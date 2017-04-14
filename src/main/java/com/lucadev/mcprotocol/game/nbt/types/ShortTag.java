package com.lucadev.mcprotocol.game.nbt.types;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.PrimaryDataTag;

import java.io.IOException;

/**
 * A signed integral type.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ShortTag extends PrimaryDataTag<Short> {

    public ShortTag() {
        super(2, "Short");
    }

    public ShortTag(short s) {
        this();
        setPayload(s);
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeShort(getPayload());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        setPayload(inputStream.readShort());
    }
}
