package com.lucadev.mcprotocol.protocol.packets.headers;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * Packet header for uncompressed packets with packet id and packet length.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class PacketLengthHeader extends PacketHeader {

    private final int length;

    /**
     * Construct the header with its packet id and length.
     *
     * @param id     packet id
     * @param length packet length
     */
    public PacketLengthHeader(int id, int length) {
        super(id);
        this.length = length;
    }

    /**
     * @return packet length
     */
    public int getLength() {
        return length;
    }

    /**
     * Write the packet's header data to the stream
     *
     * @param os the stream to write to.
     * @throws IOException when something goes wrong while writing the packet header to stream.
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, length);
        super.write(os);
    }

    @Override
    public String toString() {
        return "PacketLengthHeader{" +
                "id=0x" + Integer.toHexString(getId()).toUpperCase() + "," +
                "length=" + length +
                '}';
    }
}
