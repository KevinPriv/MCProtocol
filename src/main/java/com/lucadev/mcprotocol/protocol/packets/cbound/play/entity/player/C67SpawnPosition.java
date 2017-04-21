package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.Position;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

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
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        position = readPosition(buff.getInputStream());
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
