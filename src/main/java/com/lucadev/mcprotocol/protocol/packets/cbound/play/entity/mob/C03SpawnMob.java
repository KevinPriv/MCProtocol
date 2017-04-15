package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.mob;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C03SpawnMob extends AbstractPacket implements ReadablePacket {

    @Override
    public int getId() {
        return 0x03;
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
        //not implemented.
    }
}
