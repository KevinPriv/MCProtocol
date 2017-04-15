package com.lucadev.mcprotocol.protocol.packets;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Extension of the Packet interface.
 * This has to be implemented by all outgoing packets(serverbound) as they can write their custom payload to the stream in the write method.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface WritablePacket extends Packet {

    /**
     * Write the custom data payload to the given stream.
     * @param os the stream to write the payload to.
     * @throws IOException when something goes wrong writing the payload.
     */
    void write(DataOutputStream os) throws IOException;
}
