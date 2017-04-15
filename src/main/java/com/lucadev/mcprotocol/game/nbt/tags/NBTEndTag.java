package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.NBTTag;

import java.io.IOException;

/**
 * Used to mark the end of compound tags.
 * This tag does not have a name, so it is only ever a single byte 0.
 * It may also be the type of empty List tags.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTEndTag extends NBTTag<Object> {

    /**
     * Setup for the tag
     */
    public NBTEndTag() {
        super(0, "End");
    }

    /**
     * Get String representation of the NBT tag
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getType());
        sb.append("(None)");
        return sb.toString();
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {

    }

    /**
     * Read the payload from the stream.
     *
     * @param inputStream
     * @throws IOException
     */
    @Override
    public void readPayload(NBTInputStream inputStream) throws IOException {

    }

    /**
     * NBT NBTTag name is ignored is in the end tag
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * NBT tag name is ignored for the end tag
     *
     * @param name
     */
    @Override
    public void setName(String name) {
    }
}
