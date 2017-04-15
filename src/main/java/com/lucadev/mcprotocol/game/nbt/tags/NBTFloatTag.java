package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTPrimaryDataTag;

import java.io.IOException;

/**
 * A signed floating point type.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTFloatTag extends NBTPrimaryDataTag<Float> {

    public NBTFloatTag() {
        super(5, "Float");
    }

    public NBTFloatTag(float f) {
        this();
        setPayload(f);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeFloat(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readFloat());
    }
}
