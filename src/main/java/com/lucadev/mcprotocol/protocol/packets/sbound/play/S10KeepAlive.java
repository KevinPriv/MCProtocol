package com.lucadev.mcprotocol.protocol.packets.sbound.play;

import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S10KeepAlive extends AbstractPacket implements WritablePacket {

    private int keepAliveId;

    public S10KeepAlive(int keepAliveId) {
        this.keepAliveId = keepAliveId;
    }

    public S10KeepAlive() {
    }

    @Override
    public int getId() {
        return 0x0B;
    }

    /**
     * Write packets data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, keepAliveId);
    }
}
