package com.lucadev.mcprotocol.protocol.packets.headers;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Packet header without length
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class PacketHeader {
    private int id;

    public PacketHeader(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Write the packet header data to the stream
     * @param os
     * @throws IOException
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
