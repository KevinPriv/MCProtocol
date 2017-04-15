package com.lucadev.mcprotocol.game.nbt.tags;


import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTPrimaryDataTag;

import java.io.IOException;

/**
 * A signed integral type. Sometimes used for booleans
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTByteTag extends NBTPrimaryDataTag<Byte> {

    public NBTByteTag() {
        super(1, "Byte");
    }

    public NBTByteTag(byte b) {
        this();
        setPayload(b);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeByte(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readByte());
    }
}
