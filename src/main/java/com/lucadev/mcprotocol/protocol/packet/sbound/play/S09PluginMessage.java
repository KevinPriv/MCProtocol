package com.lucadev.mcprotocol.protocol.packet.sbound.play;

import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeString;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S09PluginMessage extends AbstractPacket implements WritablePacket {

    private String name;
    private byte[] data;

    public S09PluginMessage(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public S09PluginMessage() {

    }

    @Override
    public int getId() {
        return 0x09;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeString(os, name);
        os.write(data);
    }
}
