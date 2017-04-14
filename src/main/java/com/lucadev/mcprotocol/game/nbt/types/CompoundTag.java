package com.lucadev.mcprotocol.game.nbt.types;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.CollectionTag;
import com.lucadev.mcprotocol.game.nbt.tags.Tag;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A list of fully formed types, including their IDs, names, and payloads.
 * No two types may have the same name.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class CompoundTag extends CollectionTag<Tag> {

    public CompoundTag() {
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
        for (Tag tag : getPayload()) {
            outputStream.writeTag(tag);
        }
        outputStream.writeTag(new EndTag());
    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {
        Tag readTag = inputStream.readTag();
        while(!(readTag instanceof EndTag)) {
            add(readTag);
            readTag = inputStream.readTag();
        }
    }
}
