package com.lucadev.mcprotocol.protocol.network.client.impl;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.ProtocolException;
import com.lucadev.mcprotocol.protocol.network.client.AbstractNetClient;
import com.lucadev.mcprotocol.protocol.packets.Packet;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;
import com.lucadev.mcprotocol.protocol.packets.UndefinedPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;
import com.lucadev.mcprotocol.protocol.packets.headers.PacketLengthHeader;
import com.lucadev.mcprotocol.util.CompressionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.lucadev.mcprotocol.protocol.VarHelper.*;

/**
 * Most basic net client implementation.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultNetClient extends AbstractNetClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultNetClient.class);

    private boolean compression;
    private int compressionThreshold;

    private static final boolean PRINT_TRAFFIC = false;

    public DefaultNetClient(Bot bot) {
        super(bot);
        logger.info("Initialized DefaultNetClient");
    }

    /**
     * Force write packet to the getConnection().
     * @param packet packet to write to the getConnection().
     * @throws IOException when something goes wrong while writing the packet to the getConnection().
     */
    @Override
    protected synchronized void writePacket(WritablePacket packet) throws IOException {
        if (PRINT_TRAFFIC) {
            logger.info("SEND 0x{}", Integer.toHexString(packet.getId()).toUpperCase());
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream packetData = new DataOutputStream(bytes);

        //WRITE PACKET ID
        packetData.writeByte(packet.getId());
        //WRITE PACKET SPECIFIC DATA SUCH AS FIELDS
        packet.write(packetData);
        packetData.flush();

        int dataSize = bytes.size();
        byte[] uncompressedData = bytes.toByteArray();
        if (compression) {
            //we have to compress it.
            byte[] compressedData = CompressionUtil.compress(uncompressedData.clone());
            int packetLength = varIntLength(dataSize) + compressedData.length;
            //specs say if datalength = 0 or under threshold then send uncompressed
            if (dataSize == 0 || dataSize <= compressionThreshold) {
                //write data length which is 0 in this case
                writeVarInt(getConnection().getDataOutputStream(), varIntLength(0) + dataSize);
                writeVarInt(getConnection().getDataOutputStream(), 0);
                //write data uncompressed
                getConnection().getDataOutputStream().write(uncompressedData);
            } else {
                logger.info("Writing packets compressed 0x{}", Integer.toHexString(packet.getId()).toUpperCase());
                //write uncompressed size
                writeVarInt(getConnection().getDataOutputStream(), packetLength);
                writeVarInt(getConnection().getDataOutputStream(), dataSize);
                //write compressed data
                getConnection().getDataOutputStream().write(compressedData);
            }
        } else {
            //no compression
            //write packets size
            writeVarInt(getConnection().getDataOutputStream(), dataSize);
            //WRITE PACKET DATA
            getConnection().getDataOutputStream().write(uncompressedData);
        }
        //logger.info("Wrote packets {} to stream with length {}", "0x" + Integer.toHexString(packets.getId()).toUpperCase(), bytes.size());
    }

    /**
     * Sends a packet to the getConnection().
     * This implementation does not enable queues.
     * @param packet the packet to write.
     * @throws IOException gets thrown when something goes wrong while trying to send the packet.
     */
    @Override
    public void sendPacket(WritablePacket packet) throws IOException {
        //TODO: optional queue management. Only if connection state is PLAY
        writePacket(packet);
    }

    /**
     * Directly read a packet from the getConnection().
     * @return the read packet.
     * @throws IOException when something goes wrong while trying to read the packet.
     */
    @Override
    public synchronized ReadablePacket readPacket() throws IOException {
        int packetId = -1;
        byte[] data;
        if (!compression) {
            PacketLengthHeader header = readHeader();
            packetId = header.getId();
            data = new byte[header.getLength() - varIntLength(header.getId())];
            getConnection().getDataInputStream().readFully(data);
        } else {
            DataInputStream is = getConnection().getDataInputStream();

            //LEngth of datalength bytes + compressed size
            int packetLength = readVarInt(is);
            int dataLength = readVarInt(is);//uncompressed length of packetid + data or 0
            //Length of the remaining bytes from the packets.
            int compressedLength = packetLength - varIntLength(dataLength);

            if (dataLength == 0) {
                //rest of packets is uncompressed
                packetId = readVarInt(is);
                data = new byte[compressedLength - varIntLength(packetId)];
                is.readFully(data);
            } else {
                if (dataLength < compressionThreshold) {
                    throw new ProtocolException("Compressed size under threshold! Minimum: "
                            + compressionThreshold + " received: " + dataLength);
                }
                //packets is compressed
                byte[] compressedData = new byte[compressedLength];
                is.readFully(compressedData);
                byte[] uncompressed = CompressionUtil.decompress(compressedData);
                ByteArrayInputStream b = new ByteArrayInputStream(uncompressed);
                DataInputStream uncomp = new DataInputStream(b);
                packetId = readVarInt(uncomp);
                data = new byte[uncompressed.length - varIntLength(packetId)];
                System.arraycopy(uncompressed, varIntLength(packetId), data, 0, data.length);
            }
        }

        Protocol protocol = getBot().getProtocol();
        //logger.info("RECEIVED: {}", new PacketLengthHeader(packetId, data.length + 1));
        Packet packet = protocol.resolvePacket(packetId, protocol.getCurrentState());
        if (packet == null) {
            //logger.info("Could not resolve packets: 0x{} Returning generic packets.", Integer.toHexString(packetId).toUpperCase());
            packet = new UndefinedPacket(packetId);
        }
        if (!(packet instanceof ReadablePacket)) {
            throw new ProtocolException("Packet " + packet.getClass().getName() + " needs to implement ReadablePacket!!!");
        }

        ReadablePacket readablePacket = (ReadablePacket) packet;
        ByteArrayInputStream b = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(b);
        readablePacket.read(getBot(), dis, data.length);
        dis.close();
        if (PRINT_TRAFFIC) {
            logger.info("RECEIVE: 0x{}", Integer.toHexString(readablePacket.getId()).toUpperCase());
        }
        return readablePacket;
    }

    /**
     * Reads packet length and id into a PacketLengthHeader.
     * @return packet header consisting of packet length and packet id.
     * @throws IOException when we could not read the header.
     */
    @Override
    public PacketLengthHeader readHeader() throws IOException {
        int length = readVarInt(getConnection().getDataInputStream());
        if (length == -1) throw new IOException("Server prematurely closed stream!");

        int id = readVarInt(getConnection().getDataInputStream());
        return new PacketLengthHeader(id, length);
    }

    /**
     * Create packet header containing the packet size, packet id and data payload.
     * @param packet the packet to create the header for.
     * @param data the data payload of the packet.
     * @return header generated from the given parameters.
     */
    @Override
    public PacketLengthHeader createHeader(Packet packet, byte[] data) {
        int id = packet.getId();
        int size = data.length + varIntLength(id);//+1 due to the fact that the packets id is also 1 byte long
        return new PacketLengthHeader(id, size);
    }

    /**
     * Enables packet compression.
     * @param threshold minimal packet size before packets are compressed into stream.
     */
    @Override
    public void enableCompression(int threshold) {
        if (threshold <= 0) {
            return;
        }
        compression = true;
        compressionThreshold = threshold;
        logger.info("Setup compression with threshold {}", threshold);
    }

    /**
     * Shuts down/closes the networking client by closing streams, connections etc..
     */
    @Override
    public void close() throws IOException {
        getConnection().close();
    }

    /**
     * Method that gets called externally when we are free to read and process an incoming packet.
     * @throws IOException when something goes wrong reading from the getConnection().
     */
    @Override
    public void read() {
        try {
            ReadablePacket packet = readPacket();
            getBot().getProtocol().handlePacket(packet);
        } catch (IOException e) {
            getBot().getProtocol().getTickEngine().stop();
            try {
                getBot().getConnection().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            System.exit(1);
        }

    }

}
