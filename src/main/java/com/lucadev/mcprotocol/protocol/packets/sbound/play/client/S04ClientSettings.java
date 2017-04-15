package com.lucadev.mcprotocol.protocol.packets.sbound.play.client;

import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeString;
import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

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
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        //writes default values
        writeString(os, "en_GB");//locale
        os.writeByte(10);//client side render distance, in chunks
        writeVarInt(os, 0);//chat mode, 0=enabled. 1=commands only, 2=hidden
        os.writeBoolean(true);//chat colors enabled
        os.writeByte(0);//displayed skin part bit mask
        writeVarInt(os, 1);//right hand as main hand. 0=left, 1=right
    }
}
