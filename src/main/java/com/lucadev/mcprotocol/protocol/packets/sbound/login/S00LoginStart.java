package com.lucadev.mcprotocol.protocol.packets.sbound.login;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

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
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeVarString(username);
    }
}
