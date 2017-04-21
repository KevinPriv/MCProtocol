package com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C46PlayerPositionLook extends AbstractPacket implements ReadablePacket {

    private double x, y, z;
    private float yaw;
    private float pitch;
    private int teleportId;

    @Override
    public int getId() {
        return 0x2E;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        x = buff.readDouble();
        y = buff.readDouble();
        z = buff.readDouble();
        yaw = buff.readFloat();
        pitch = buff.readFloat();
        byte flag = buff.readByte();
        teleportId = buff.readVarInt();

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public int getTeleportId() {
        return teleportId;
    }
}
