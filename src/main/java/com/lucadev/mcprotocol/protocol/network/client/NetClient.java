package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.packets.Packet;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;
import com.lucadev.mcprotocol.protocol.packets.headers.PacketLengthHeader;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * Packet-based networking client.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface NetClient {

    /**
     * Sends a packet to the connection.
     * This implementation may enable queueing and other techniques.
     * @param packet the packet to write.
     * @throws IOException gets thrown when something goes wrong while trying to send the packet.
     */
    void sendPacket(WritablePacket packet) throws IOException;

    /**
     * Directly read a packet from the connection.
     * @return the read packet.
     * @throws IOException when something goes wrong while trying to read the packet.
     */
    ReadablePacket readPacket() throws IOException;

    /**
     * Reads packet length and id into a PacketLengthHeader.
     * @return packet header consisting of packet length and packet id.
     * @throws IOException when we could not read the header.
     */
    PacketLengthHeader readHeader() throws IOException;

    /**
     * Create packet header containing the packet size, packet id and data payload.
     * @param packet the packet to create the header for.
     * @param data the data payload of the packet.
     * @return header generated from the given parameters.
     */
    PacketLengthHeader createHeader(Packet packet, byte[] data);

    /**
     * Enables packet compression.
     * @param threshold minimal packet size before packets are compressed into stream.
     */
    void enableCompression(int threshold);

    /**
     * Shuts down/closes the networking client by closing streams, connections etc..
     */
    void disconnect() throws IOException;

    /**
     * Method that gets called externally when we are free to read and process an incoming packet.
     * @throws IOException when something goes wrong reading from the connection.
     */
    void read() throws IOException;
}
