package com.lucadev.mcprotocol.game.nbt.types;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.Tag;

import java.io.IOException;

/**
 * An array of TAG_Int's payloads.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class IntArrayTag extends Tag<int[]> {

    public IntArrayTag() {
        super(11, "Int_Array");
    }

    public IntArrayTag(int[] data) {
        this();
        setPayload(data);
    }

    /**
     * Get String representation of the NBT tag
     *
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        outputStream.writeInt(getPayload().length);
        for (int i : getPayload()) {
            outputStream.writeInt(i);
        }
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        int size = inputStream.readInt();
        int[] data = new int[size];
        for(int x = 0; x < size; x++) {
            data[x] = inputStream.readInt();
        }
        setPayload(data);
    }
}
