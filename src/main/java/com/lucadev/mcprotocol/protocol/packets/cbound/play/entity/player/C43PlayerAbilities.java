package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.PlayerAbilities;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C43PlayerAbilities extends AbstractPacket implements ReadablePacket {

    private PlayerAbilities playerAbilities;

    @Override
    public int getId() {
        return 0x2B;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        byte b = buff.readByte();
        float flySpeed = buff.readFloat();
        float fovModifier = buff.readFloat();
        playerAbilities = new PlayerAbilities(b, flySpeed, fovModifier);
    }

    public PlayerAbilities getPlayerAbilities() {
        return playerAbilities;
    }
}
