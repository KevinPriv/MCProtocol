package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.Position;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readPosition;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C67SpawnPosition extends AbstractPacket implements ReadablePacket {

    private Position position;

    @Override
    public int getId() {
        return 0x43;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        position = readPosition(is);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "C67SpawnPosition{" +
                "position=" + position +
                '}';
    }
}
