package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.packet.Packet;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;
import com.lucadev.mcprotocol.protocol.packet.headers.PacketLengthHeader;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * Networking client for the protocol
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface NetClient {

    /**
     * Obtain the raw connection
     * @return
     */
    Connection getConnection();

    /**
     * Force writes a packet to the stream. No checking
     * @param packet
     * @throws IOException
     */
    void writePacket(WritablePacket packet) throws IOException;

    /**
     * Write a packet to stream following any possible queue techniques
     * @param packet
     */
    void sendPacket(WritablePacket packet);

    /**
     * Reads a packet directly from stream. No checking
     * @return
     * @throws IOException
     */
    ReadablePacket readPacket() throws IOException;

    /**
     * Reads packet length and id from stream. No checking.
     * @return
     * @throws IOException
     */
    PacketLengthHeader readHeader() throws IOException;

    /**
     * Reads packet length as varint then returns byte array with the given length.
     * @return
     * @throws IOException
     */
    byte[] readResponse() throws IOException;

    /**
     * Create packet header
     * @param packet
     * @param data
     * @return
     */
    PacketLengthHeader createHeader(Packet packet, byte[] data);

    SecretKey getSharedKey();

    void setSharedKey(SecretKey secretKey);

    boolean isEncrypting();

    boolean isDecrypting();

    void enableEncryption();

    void enableDecryption();

    void enableCompression(int treshold);

    void shutdown();

    /**
     * Tick dedicated to reading
     */
    void tickRead();
}
