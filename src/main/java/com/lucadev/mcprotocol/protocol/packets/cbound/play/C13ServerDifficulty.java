package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C13ServerDifficulty extends AbstractPacket implements ReadablePacket {

    private Difficulty difficulty;

    @Override
    public int getId() {
        return 0x0D;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        byte diff = buff.readByte();
        difficulty = Difficulty.getDifficulty(diff);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
