package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;

import java.io.IOException;

/**
 * NBT NBTTag
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class NBTTag<T> {

    public static final String TAG_PREFIX = "TAG_";
    public static final String NEWLINE = "\r\n";

    /**
     * NBT NBTTag ID
     */
    private final int id;

    /**
     * NBTTag type
     */
    private final String type;

    /**
     * Optional tag name
     */
    private String name;

    /**
     * NBT NBTTag Payload
     */
    private T payload;

    /**
     * Initialize a NBT tag
     * @param id nbt tag id
     * @param type nbt tag type
     */
    public NBTTag(int id, String type) {
        this.id = id;
        //TAG_End for example
        if(!type.startsWith(TAG_PREFIX)) {
            type = TAG_PREFIX + type;
        }
        this.type = type;
    }

    /**
     * NBT NBTTag type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * NBTTag ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * NBTTag payload
     * @return
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Set the tag payload
     * @param data
     */
    public void setPayload(T data) {
        payload = data;
    }

    /**
     * NBT NBTTag name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set nbt tag name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get String representation of the NBT tag
     * @return
     */
    public abstract String toString();

    /**
     * Write the payload of the tag to the stream.
     * @param outputStream
     * @throws IOException
     */
    public abstract void writePayload(NBTOutputStream outputStream) throws IOException;

    /**
     * Read the payload from the stream.
     * @param inputStream
     * @throws IOException
     */
    public abstract void readPayload(NBTInputStream inputStream) throws IOException;
}
