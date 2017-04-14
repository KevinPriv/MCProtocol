package com.lucadev.mcprotocol.game.nbt.types;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.PrimaryDataTag;

import java.io.IOException;

/**
 * A signed integral type.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class IntTag extends PrimaryDataTag<Integer> {

    public IntTag() {
        super(3, "Int");
    }

    public IntTag(int data) {
        this();
        setPayload(data);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeInt(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readInt());
    }
}
