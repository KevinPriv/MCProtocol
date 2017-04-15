package com.lucadev.mcprotocol.protocol.packet.sbound.login;

import com.lucadev.mcprotocol.protocol.VarHelper;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S00LoginStart extends AbstractPacket implements WritablePacket {

    private String username;

    public S00LoginStart() {

    }

    public S00LoginStart(String username) {
        this.username = username;
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
        VarHelper.writeString(os, username);
    }
}
