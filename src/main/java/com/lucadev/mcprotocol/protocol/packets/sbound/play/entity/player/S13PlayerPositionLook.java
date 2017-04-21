package com.lucadev.mcprotocol.protocol.packets.sbound.play.entity.player;

import com.lucadev.mcprotocol.game.entity.player.Player;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S13PlayerPositionLook extends AbstractPacket implements WritablePacket {


    private Player player;

    public S13PlayerPositionLook() {
    }

    public S13PlayerPositionLook(Player player) {
        this.player = player;
    }

    @Override
    public int getId() {
        return 0x0D;
    }

    /**
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeDouble(player.getPosX());
        buff.writeDouble(player.getPosY());
        buff.writeDouble(player.getPosZ());
        buff.writeFloat(player.getYaw());
        buff.writeFloat(player.getPitch());
        buff.writeBoolean(player.isOnGround());
    }
}
