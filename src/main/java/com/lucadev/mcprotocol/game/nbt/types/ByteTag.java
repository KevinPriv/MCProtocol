package com.lucadev.mcprotocol.game.nbt.types;


import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.PrimaryDataTag;

import java.io.IOException;

/**
 * A signed integral type. Sometimes used for booleans
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ByteTag extends PrimaryDataTag<Byte> {

    public ByteTag() {
        super(1, "Byte");
    }

    public ByteTag(byte b) {
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
