package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTTag;

import java.io.IOException;

/**
 * An array of bytes.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTByteArrayTag extends NBTTag<byte[]> {

    public NBTByteArrayTag() {
        super(7, "Byte_Array");
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
        outputStream.write(getPayload());
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
        byte[] data = new byte[size];
        inputStream.readFully(data);
        setPayload(data);
    }
}
