package com.lucadev.mcprotocol.protocol.packet.cbound.login;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C03SetCompression extends AbstractPacket implements ReadablePacket {

    private int threshold;

    @Override
    public int getId() {
        return 0x03;
    }

    /**
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        threshold = readVarInt(is);
    }

    public int getThreshold() {
        return threshold;
    }
}
