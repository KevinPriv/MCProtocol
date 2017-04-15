package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.NBTEndTag;
import com.lucadev.mcprotocol.protocol.network.stream.EndianSwitchableOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;

import static com.lucadev.mcprotocol.game.nbt.NBT.CHARSET;

/**
 * NBT output stream.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTOutputStream extends EndianSwitchableOutputStream {


    public NBTOutputStream(OutputStream outputStream) {
        this(outputStream, ByteOrder.BIG_ENDIAN);
    }

    public NBTOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
        super(outputStream, byteOrder);
    }

    /**
     * Writes a NBT NBTTag to the stream.
     *
     * @param NBTTag the NBTTag to write
     * @throws IOException
     */
    public void writeTag(NBTTag NBTTag) throws IOException {
        writeByte(NBTTag.getId());
        if (NBTTag instanceof NBTEndTag) {
            return;
        }
        String name = NBTTag.getName();
        if (name == null || name.isEmpty()) {
            writeShort(0);
        } else {
            byte[] nameBytes = name.getBytes(CHARSET);
            writeShort(nameBytes.length);
            write(nameBytes);
        }
        NBTTag.writePayload(this);
    }
}
