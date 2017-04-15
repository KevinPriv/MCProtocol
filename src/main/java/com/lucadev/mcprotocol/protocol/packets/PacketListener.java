package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.Bot;

/**
 * Listener interface used to listen for specified packets.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.protocol.packets.Packet
 */
public interface PacketListener<T extends ReadablePacket> {

    /**
     * Method that will be invoked on the listener.     *
     * @param bot instance that received the packet.
     * @param packet the actual packet.
     */
    void onPacket(Bot bot, T packet);
}
