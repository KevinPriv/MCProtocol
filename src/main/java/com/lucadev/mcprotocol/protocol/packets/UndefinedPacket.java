package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.Bot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Packet implementation that will be used for unhandled packets.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class UndefinedPacket extends AbstractPacket implements ReadablePacket, WritablePacket {

    private final int packetId;

    /**
     * Construct the packet with its unhandled packet id.
     * @param id the packet id.
     */
    public UndefinedPacket(int id) {
        packetId = id;
    }

    /**
     * @return packet ID
     */
    @Override
    public int getId() {
        return packetId;
    }

    /**
     * Write the custom data payload to the given stream.
     * In the case of an undefined packet we will not implement this method. The bytes will simply be skipped.
     * @param os the stream to write the payload to.
     * @throws IOException when something goes wrong writing the payload.
     */
    @Override
    public void write(DataOutputStream os) throws IOException {

    }

    /**
     * Read the data payload from the packet in here. This does not include packets id and fields.
     * In the case of an undefined packet we will not implement this method. The bytes will simply be skipped.
     * @param bot the bot that received the packet.
     * @param is stream that contains the packet data.
     * @param totalSize size of the available data in the stream
     * @throws IOException gets thrown when something goes wrong reading the data payload
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {

    }
}
