package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.io.ByteBuffer;
import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

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
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        String channelName = buff.readVarString(20);
        //since we cannot know the size of the buffer we'll read until EOF is reached.
        this.channelName = channelName;
        ByteBuffer bb = new ByteBuffer();
        try {
            int i = buff.read();
            while (i != -1) {
                bb.write(i);
                i = buff.read();
            }
        } catch (Exception ex) {
            //skip
        } finally {
            this.data = bb.toByteArray();
        }

    }

    public String getChannelName() {
        return channelName;
    }

    public byte[] getData() {
        return data;
    }
}
