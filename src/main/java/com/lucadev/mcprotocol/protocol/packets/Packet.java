package com.lucadev.mcprotocol.protocol.packets;

/**
 * Interface which is implemented by all game packets.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Packet {

    /**
     * @return packet ID
     */
    int getId();
}
