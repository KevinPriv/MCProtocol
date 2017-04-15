package com.lucadev.mcprotocol.protocol.packets;

import com.lucadev.mcprotocol.Bot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class UndefinedPacket extends AbstractPacket implements ReadablePacket, WritablePacket {

    private int packetId;

    public UndefinedPacket(int id) {
        packetId = id;
    }

    @Override
    public int getId() {
        return packetId;
    }

    /**
     * Write packets data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {

    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {

    }
}
