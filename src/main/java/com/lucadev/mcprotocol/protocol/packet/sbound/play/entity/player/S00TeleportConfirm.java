package com.lucadev.mcprotocol.protocol.packet.sbound.play.entity.player;

import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S00TeleportConfirm extends AbstractPacket implements WritablePacket {

    private int teleportId;

    public S00TeleportConfirm(int teleportId) {
        this.teleportId = teleportId;
    }

    public S00TeleportConfirm() {
    }

    @Override
    public int getId() {
        return 0x00;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, teleportId);
    }


}
