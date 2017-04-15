package com.lucadev.mcprotocol.protocol.packet.cbound.play.entity.player;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C46PlayerPositionLook extends AbstractPacket implements ReadablePacket{

    private double x, y, z;
    private float yaw;
    private float pitch;
    private int teleportId;

    @Override
    public int getId() {
        return 0x2E;
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
        x = is.readDouble();
        y = is.readDouble();
        z = is.readDouble();
        yaw = is.readFloat();
        pitch = is.readFloat();
        byte flag = is.readByte();
        teleportId = readVarInt(is);

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
