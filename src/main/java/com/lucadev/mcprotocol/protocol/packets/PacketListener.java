package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.Bot;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface PacketListener<T extends ReadablePacket> {

    /**
     * Event on the packet.
     * @param bot
     * @param packet
     */
    void onPacket(Bot bot, T packet);
}
