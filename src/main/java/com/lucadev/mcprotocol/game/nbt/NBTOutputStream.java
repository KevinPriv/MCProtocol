package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.Tag;
import com.lucadev.mcprotocol.game.nbt.types.EndTag;
import com.lucadev.mcprotocol.network.io.EndianSwitchableOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;

import static com.lucadev.mcprotocol.game.nbt.NBT.*;

/**
 * NBT output stream.
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
     * Writes a NBT tag to the stream.
     * @param tag the tag to write
     * @throws IOException
     */
    public void writeTag(Tag tag) throws IOException {
        writeByte(tag.getId());
        if(tag instanceof EndTag) {
            return;
        }
        String name = tag.getName();
        if(name == null || name.isEmpty()) {
            writeShort(0);
        } else {
            byte[] nameBytes = name.getBytes(CHARSET);
            writeShort(nameBytes.length);
            write(nameBytes);
        }
        tag.writePayload(this);
    }
}
