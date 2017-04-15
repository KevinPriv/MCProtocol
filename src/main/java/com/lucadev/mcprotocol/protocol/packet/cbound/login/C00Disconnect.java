package com.lucadev.mcprotocol.protocol.packet.cbound.login;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C00Disconnect extends AbstractPacket implements ReadablePacket {

    @Override
    public int getId() {
        return 0x00;
    }

    /**
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        //TODO: Chat field type
    }
}
