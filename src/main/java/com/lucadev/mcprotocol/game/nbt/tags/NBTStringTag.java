package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBT;
import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTPrimaryDataTag;

import java.io.IOException;

/**
 * A UTF-8 string. It has a size, rather than being null terminated.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTStringTag extends NBTPrimaryDataTag<String> {

    public NBTStringTag() {
        super(8, "String");
    }

    public NBTStringTag(String str) {
        this();
        setPayload(str);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        byte[] bytes = getPayload().getBytes(NBT.CHARSET);
        outputStream.writeShort(bytes.length);
        outputStream.write(bytes);
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        int size = inputStream.readShort();
        byte[] bytes = new byte[size];
        inputStream.readFully(bytes);
        String str = new String(bytes, NBT.CHARSET);
        setPayload(str);
    }
}
