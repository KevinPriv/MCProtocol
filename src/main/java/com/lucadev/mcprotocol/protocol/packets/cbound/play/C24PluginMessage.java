package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;
import static com.lucadev.mcprotocol.protocol.VarHelper.varIntLength;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C24PluginMessage extends AbstractPacket implements ReadablePacket {

    private String channelName;
    private byte[] data;

    @Override
    public int getId() {
        return 0x18;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        String channelName = readString(is, 20);
        int dataSize = totalSize - varIntLength(channelName.length()) - channelName.length();
        byte[] data = new byte[dataSize];
        is.readFully(data);
        this.channelName = channelName;
        this.data = data;

    }

    public String getChannelName() {
        return channelName;
    }

    public byte[] getData() {
        return data;
    }
}
