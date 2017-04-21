package com.lucadev.mcprotocol.protocol.packets.sbound.play;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

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
     * Write packets data
     *
     * @param buff databuffer
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeVarString(message);
    }
}
