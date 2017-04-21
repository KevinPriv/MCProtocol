package com.lucadev.mcprotocol.protocol.packets.sbound.play.client;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S04ClientSettings extends AbstractPacket implements WritablePacket {

    @Override
    public int getId() {
        return 0x04;
    }

    /**
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        //writes default values
        buff.writeVarString("en_GB");
        buff.writeByte(10);//client side render distance, in chunks
        buff.writeVarInt(0);//chat mode, 0=enabled. 1=commands only, 2=hidden
        buff.writeBoolean(true);//chat colors enabled
        buff.writeByte(0);//displayed skin part bit mask
        buff.writeVarInt(1);//right hand as main hand. 0=left, 1=right
    }
}
