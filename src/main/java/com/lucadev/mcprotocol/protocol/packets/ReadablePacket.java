package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.Bot;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Extension of the Packet interface.
 * This interface is required for all packets that are read from stream(clientbound/coming from the server)
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ReadablePacket extends Packet {

    /**
     * Read the data payload from the packet in here. This does not include packets id and fields.
     * @param bot the bot that received the packet.
     * @param is stream that contains the packet data.
     * @param totalSize size of the available data in the stream
     * @throws IOException gets thrown when something goes wrong reading the data payload
     */
    void read(Bot bot, DataInputStream is, int totalSize) throws IOException;
}
