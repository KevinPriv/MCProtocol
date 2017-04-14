package com.lucadev.mcprotocol.protocol.packets.sbound.play;

import com.lucadev.mcprotocol.game.entity.Player;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
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
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        os.writeDouble(player.getPosX());
        os.writeDouble(player.getPosY());
        os.writeDouble(player.getPosZ());
        os.writeFloat(player.getYaw());
        os.writeFloat(player.getPitch());
        os.writeBoolean(player.isOnGround());
    }
}
