package com.lucadev.mcprotocol.protocol.packet.cbound.play;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
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
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        byte diff = is.readByte();
        difficulty = Difficulty.getDifficulty(diff);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
