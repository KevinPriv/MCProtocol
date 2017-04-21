package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;

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
     *
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
     *
     * @param buff the buffer to write the payload to.
     * @throws IOException when something goes wrong writing the payload.
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {

    }

    /**
     * Read the data payload from the packet in here. This does not include packets id and fields.
     * In the case of an undefined packet we will not implement this method. The bytes will simply be skipped.
     *
     * @param bot  the bots that received the packet.
     * @param buff buffer that contains the packet data.
     * @throws IOException gets thrown when something goes wrong reading the data payload
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {

    }
}
