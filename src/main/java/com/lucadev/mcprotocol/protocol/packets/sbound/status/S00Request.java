package com.lucadev.mcprotocol.protocol.packets.sbound.status;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S00Request extends AbstractPacket implements WritablePacket {

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

    }
}
