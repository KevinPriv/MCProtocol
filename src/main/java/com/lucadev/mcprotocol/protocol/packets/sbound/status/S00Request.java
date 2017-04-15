package com.lucadev.mcprotocol.protocol.packets.sbound.status;

import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
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
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {

    }
}
