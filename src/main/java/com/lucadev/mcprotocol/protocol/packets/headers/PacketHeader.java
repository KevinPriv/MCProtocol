package com.lucadev.mcprotocol.protocol.packets.headers;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Packet header without length.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class PacketHeader {

    private final int id;

    /**
     * Construct the packet header with a packet id.
     *
     * @param id read packet id.
     */
    public PacketHeader(int id) {
        this.id = id;
    }

    /**
     * @return packet ID
     */
    public int getId() {
        return id;
    }

    /**
     * Write the packet's header data to the stream
     *
     * @param os the stream to write to.
     * @throws IOException when something goes wrong while writing the packet header to stream.
     */
    public void write(DataOutputStream os) throws IOException {
        os.writeByte(id);
    }

    @Override
    public String toString() {
        return "PacketHeader{" +
                "id=0x" + Integer.toHexString(id).toUpperCase() +
                '}';
    }
}
