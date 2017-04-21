package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C52EntityHeadLook extends AbstractPacket implements ReadablePacket {

    @Override
    public int getId() {
        return 0x34;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param bot
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {

    }
}
