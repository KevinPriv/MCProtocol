package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTCollectionTag;
import com.lucadev.mcprotocol.game.nbt.NBTTag;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A list of fully formed tags, including their IDs, names, and payloads.
 * No two tags may have the same name.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTCompoundTag extends NBTCollectionTag<NBTTag> {

    public NBTCompoundTag() {
        super(10, "Compound");
        setPayload(new ArrayList<>());
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        for (NBTTag NBTTag : getPayload()) {
            outputStream.writeTag(NBTTag);
        }
        outputStream.writeTag(new NBTEndTag());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        NBTTag readNBTTag = inputStream.readTag();
        while(!(readNBTTag instanceof NBTEndTag)) {
            add(readNBTTag);
            readNBTTag = inputStream.readTag();
        }
    }
}
