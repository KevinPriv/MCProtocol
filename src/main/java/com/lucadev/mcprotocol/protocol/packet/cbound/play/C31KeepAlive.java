package com.lucadev.mcprotocol.protocol.packet.cbound.play;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C31KeepAlive extends AbstractPacket implements ReadablePacket {

    private int keepAliveId;

    @Override
    public int getId() {
        return 0x1F;
    }

    /**
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        keepAliveId = readVarInt(is);
    }

    public int getKeepAliveId() {
        return keepAliveId;
    }
}
