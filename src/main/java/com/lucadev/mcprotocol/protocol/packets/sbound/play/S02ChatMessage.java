package com.lucadev.mcprotocol.protocol.packets.sbound.play;

import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeString;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S02ChatMessage extends AbstractPacket implements WritablePacket {

    private String message;

    public S02ChatMessage(String message) {
        this.message = message;
    }

    public S02ChatMessage() {
    }

    @Override
    public int getId() {
        return 0x02;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeString(os, message);
    }
}
