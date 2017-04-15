package com.lucadev.mcprotocol.protocol.packets;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Means a packets can write their datas
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface WritablePacket extends Packet {

    /**
     * Write packets data
     *
     * @param os
     * @throws IOException
     */
    void write(DataOutputStream os) throws IOException;
}
