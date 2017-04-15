package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTPrimaryDataTag;

import java.io.IOException;

/**
 * A signed integral type.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTLongTag extends NBTPrimaryDataTag<Long> {
    public NBTLongTag() {
        super(4, "Long");
    }

    public NBTLongTag(long l) {
        this();
        setPayload(l);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeLong(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readLong());
    }
}
