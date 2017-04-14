package com.lucadev.mcprotocol.game.nbt.types;

import com.lucadev.mcprotocol.game.nbt.NBT;
import com.lucadev.mcprotocol.game.nbt.NBTException;
import com.lucadev.mcprotocol.game.nbt.NBTInputStream;
import com.lucadev.mcprotocol.game.nbt.NBTOutputStream;
import com.lucadev.mcprotocol.game.nbt.tags.CollectionTag;
import com.lucadev.mcprotocol.game.nbt.tags.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A list of tag payloads, without repeated tag IDs or any tag names.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ListTag<T extends Tag> extends CollectionTag<T> {

    private Class<T> tagClass;
    private boolean useExtendedName;

    public ListTag(Class<T> clazz) {
        super(9, "List");
        if(clazz == null) {
            throw new IllegalStateException("List tag class may not be null!");
        }
        this.tagClass = clazz;
        setPayload(new ArrayList<T>());
    }

    public ListTag(int tagCode) {
        this((Class<T>) NBT.getTagClassById(tagCode));
    }

    public ListTag(Class<T> clazz, List<T> data) {
        this(clazz);
        setPayload(data);
    }

    public ListTag(int typeId, List<T> data) {
        this((Class<T>) NBT.getTagClassById(typeId), data);
    }

    /**
     * NBT Tag name
     *
     * @return
     */
    @Override
    public String getName() {
        if(useExtendedName) {
            String tagType = getTagClass().getSimpleName().substring(TAG_PREFIX.length()).toLowerCase();
            return super.getName() + " (" + tagType + ")";
        } else {
            return super.getName();
        }
    }

    /**
     * Write the payload of the tag to the stream.
     *
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void writePayload(NBTOutputStream outputStream) throws IOException {
        List<T> tags = getPayload();
        int tagType = NBT.getIdByTagClass(tagClass);
        if(tagType == -1) {
            throw new NBTException("Tag class " + tagClass.getName() + " not supported yet.");
        }
        outputStream.writeByte(tagType);
        outputStream.writeInt(getSize());
        for (T t : getPayload()) {
            if(t.getId() != tagType) {
                throw new NBTException("Unmatched tag type in tag list.");
            }
            outputStream.writeTag(t);
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
        int type = inputStream.readByte();
        int size = inputStream.readInt();
        getPayload().clear();
        for(int x = 0; x < size; x++) {
            add((T) inputStream.readTag());
        }
    }

    /**
     * Class of the tag type that's being stored.
     * @return
     */
    public Class<T> getTagClass() {
        return tagClass;
    }

    public boolean shouldUseExtendedName() {
        return useExtendedName;
    }

    public void setUseExtendedName(boolean useExtendedName) {
        this.useExtendedName = useExtendedName;
    }
}
