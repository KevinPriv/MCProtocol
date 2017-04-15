package com.lucadev.mcprotocol.game.nbt.tags;


import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTPrimaryDataTag;

import java.io.IOException;

/**
 * A signed floating point type.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTDoubleTag extends NBTPrimaryDataTag<Double> {

    public NBTDoubleTag() {
        super(6, "Double");
    }

    public NBTDoubleTag(double d) {
        this();
        setPayload(d);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeDouble(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readDouble());
    }
}
