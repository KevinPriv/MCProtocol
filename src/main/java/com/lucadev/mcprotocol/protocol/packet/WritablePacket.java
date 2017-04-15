package com.lucadev.mcprotocol.protocol.packet;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Means a packet can write their datas
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface WritablePacket extends Packet{

    /**
     * Write packet data
     * @param os
     * @throws IOException
     */
    void write(DataOutputStream os) throws IOException;
}
