package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;

import java.io.IOException;

/**
 * Extension of the Packet interface. Will also specify which type of bot is passed into the read method.
 * This interface is required for all packets that are read from stream(clientbound/coming from the server)
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ReadablePacket<T extends Bot> extends Packet {

    /**
     * Read the data payload from the packet in here. This does not include packets id and fields.
     *
     * @param bot  the bots that received the packet.
     * @param buff Data buffer that contains required data.
     * @throws IOException gets thrown when something goes wrong reading the data payload
     */
    void read(T bot, VarDataBuffer buff) throws IOException;
}
