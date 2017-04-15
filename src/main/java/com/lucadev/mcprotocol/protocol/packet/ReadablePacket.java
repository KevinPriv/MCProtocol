package com.lucadev.mcprotocol.protocol.packet;

import com.lucadev.mcprotocol.Bot;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Means a packet can read their datas
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ReadablePacket extends Packet {

    /**
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    void read(Bot bot, DataInputStream is, int totalSize) throws IOException;
}
