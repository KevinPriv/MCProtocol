package com.lucadev.mcprotocol.protocol.network.client.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.ProtocolException;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.packet.Packet;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;
import com.lucadev.mcprotocol.protocol.packet.UndefinedPacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;
import com.lucadev.mcprotocol.protocol.packet.headers.PacketLengthHeader;
import com.lucadev.mcprotocol.util.CompressionUtil;
import com.lucadev.mcprotocol.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.io.*;

import static com.lucadev.mcprotocol.protocol.VarHelper.*;

/**
 * Most basic net client there is.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class SimpleNetClient implements NetClient {

    private Bot bot;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SimpleNetClient.class);

    private SecretKey sharedKey;
    private boolean encrypting;
    private boolean decrypting;

    private boolean compression;
    private int compressionThreshold;

    private static final boolean PRINT_TRAFFIC = false;

    public SimpleNetClient(Bot bot, Connection connection) {
        this.connection = connection;
        this.bot = bot;
        logger.info("Initialized SimpleNetClient");
    }

    /**
     * Obtain the raw connection
     *
     * @return
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * Force writes a packet to the stream. No checking
     *
     * @param packet
     * @throws IOException
     */
    @Override
    public synchronized void writePacket(WritablePacket packet) throws IOException {
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
                writeVarInt(connection.getDataOutputStream(), varIntLength(0) + dataSize);
                writeVarInt(connection.getDataOutputStream(), 0);
                //write data uncompressed
                connection.getDataOutputStream().write(uncompressedData);
            } else {
                logger.info("Writing packet compressed 0x{}", Integer.toHexString(packet.getId()).toUpperCase());
                //write uncompressed size
                writeVarInt(connection.getDataOutputStream(), packetLength);
                writeVarInt(connection.getDataOutputStream(), dataSize);
                //write compressed data
                connection.getDataOutputStream().write(compressedData);
            }
        } else {
            //no compression
            //write packet size
            writeVarInt(connection.getDataOutputStream(), dataSize);
            //WRITE PACKET DATA
            connection.getDataOutputStream().write(uncompressedData);
        }
        //logger.info("Wrote packet {} to stream with length {}", "0x" + Integer.toHexString(packet.getId()).toUpperCase(), bytes.size());
    }

    /**
     * Write a packet to stream following any possible queue techniques
     *
     * @param packet
     */
    @Override
    public void sendPacket(WritablePacket packet) {
        try {
            writePacket(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a packet directly from stream. No checking
     *
     * @return
     * @throws IOException
     */
    @Override
    public synchronized ReadablePacket readPacket() throws IOException {
        int packetId = -1;
        byte[] data;
        if (!compression) {
            PacketLengthHeader header = readHeader();
            packetId = header.getId();
            data = new byte[header.getLength() - varIntLength(header.getId())];
            connection.getDataInputStream().readFully(data);
        } else {
            DataInputStream is = connection.getDataInputStream();

            //LEngth of datalength bytes + compressed size
            int packetLength = readVarInt(is);
            int dataLength = readVarInt(is);//uncompressed length of packetid + data or 0
            //Length of the remaining bytes from the packet.
            int compressedLength = packetLength - varIntLength(dataLength);

            if (dataLength == 0) {
                //rest of packet is uncompressed
                packetId = readVarInt(is);
                data = new byte[compressedLength - varIntLength(packetId)];
                is.readFully(data);
            } else {
                if (dataLength < compressionThreshold) {
                    throw new ProtocolException("Compressed size under threshold! Minimum: "
                            + compressionThreshold + " received: " + dataLength);
                }
                //packet is compressed
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

        Protocol protocol = bot.getProtocol();
        //logger.info("RECEIVED: {}", new PacketLengthHeader(packetId, data.length + 1));
        Packet packet = protocol.resolvePacket(packetId, protocol.getCurrentState());
        if (packet == null) {
            //logger.info("Could not resolve packet: 0x{} Returning generic packet.", Integer.toHexString(packetId).toUpperCase());
            packet = new UndefinedPacket(packetId);
        }
        if (!(packet instanceof ReadablePacket)) {
            throw new ProtocolException("Packet " + packet.getClass().getName() + " needs to implement ReadablePacket!!!");
        }

        ReadablePacket readablePacket = (ReadablePacket) packet;
        ByteArrayInputStream b = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(b);
        readablePacket.read(bot, dis, data.length);
        dis.close();
        if (PRINT_TRAFFIC) {
            logger.info("RECEIVE: 0x{}", Integer.toHexString(readablePacket.getId()).toUpperCase());
        }
        return readablePacket;
    }

    /**
     * Reads packet length and id from stream. No checking.
     *
     * @return
     * @throws IOException
     */
    @Override
    public PacketLengthHeader readHeader() throws IOException {
        int length = readVarInt(connection.getDataInputStream());
        if (length == -1) throw new IOException("Server prematurely closed stream!");

        int id = readVarInt(connection.getDataInputStream());
        return new PacketLengthHeader(id, length);
    }

    /**
     * Reads packet length as varint then returns byte array with the given length.
     *
     * @return
     * @throws IOException
     */
    @Override
    public byte[] readResponse() throws IOException {
        int length = readVarInt(connection.getDataInputStream());
        byte[] bytes = new byte[length];
        connection.getDataInputStream().readFully(bytes);
        return bytes;
    }

    /**
     * Create packet header
     *
     * @param packet
     * @param data
     * @return
     */
    @Override
    public PacketLengthHeader createHeader(Packet packet, byte[] data) {
        int id = packet.getId();
        int size = data.length + 1;//+1 due to the fact that the packet id is also 1 byte long
        return new PacketLengthHeader(id, size);
    }

    @Override
    public SecretKey getSharedKey() {
        return sharedKey;
    }

    @Override
    public void setSharedKey(SecretKey secretKey) {
        if (this.sharedKey != null) {
            throw new IllegalStateException("May not set shared key again.");
        }
        this.sharedKey = secretKey;
    }

    @Override
    public boolean isEncrypting() {
        return encrypting;
    }

    @Override
    public boolean isDecrypting() {
        return decrypting;
    }

    @Override
    public void enableEncryption() {
        if (isEncrypting()) {
            throw new IllegalStateException("Already encrypting.");
        }
        if (sharedKey == null) {
            throw new IllegalStateException("Shared key is required!");
        }
        Connection con = bot.getConnection();
        con.setDataOutputStream(new DataOutputStream(EncryptionUtil.encryptOutputStream(con.getDataOutputStream(), sharedKey)));
        encrypting = true;
        logger.info("Enabled encryption");
    }

    @Override
    public void enableDecryption() {
        if (isDecrypting()) {
            throw new IllegalStateException("Already decrypting");
        }
        if (sharedKey == null) {
            throw new IllegalStateException("Shared key is required!");
        }
        Connection con = bot.getConnection();
        con.setDataInputStream(new DataInputStream(EncryptionUtil.decryptInputStream(con.getDataInputStream(), sharedKey)));
        decrypting = true;
        logger.info("Enabled decryption");
    }

    @Override
    public void enableCompression(int treshold) {
        if (treshold <= 0) {
            return;
        }
        compression = true;
        compressionThreshold = treshold;
        logger.info("Setup compression with threshold {}", treshold);
    }

    @Override
    public void shutdown() {

    }

    /**
     * Tick dedicated to reading
     */
    @Override
    public void tickRead() {
        try {
            ReadablePacket packet = readPacket();
            bot.getProtocol().handlePacket(packet);
        } catch (IOException e) {
            bot.getProtocol().getTickEngine().stopTicking();
            try {
                bot.getConnection().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            System.exit(1);
        }

    }

}
