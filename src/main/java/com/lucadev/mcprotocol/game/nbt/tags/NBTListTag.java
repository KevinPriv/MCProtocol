package com.lucadev.mcprotocol.game.nbt.tags;

import com.lucadev.mcprotocol.game.nbt.*;
import com.lucadev.mcprotocol.game.nbt.NBTTag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A list of tag payloads, without repeated tag IDs or any tag names.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTListTag<T extends NBTTag> extends NBTCollectionTag<T> {

    private Class<T> tagClass;
    private boolean useExtendedName;

    public NBTListTag(Class<T> clazz) {
        super(9, "List");
        if(clazz == null) {
            throw new IllegalStateException("List tag class may not be null!");
        }
        this.tagClass = clazz;
        setPayload(new ArrayList<T>());
    }

    public NBTListTag(int tagCode) {
        this((Class<T>) NBT.getTagClassById(tagCode));
    }

    public NBTListTag(Class<T> clazz, List<T> data) {
        this(clazz);
        setPayload(data);
    }

    public NBTListTag(int typeId, List<T> data) {
        this((Class<T>) NBT.getTagClassById(typeId), data);
    }

    /**
     * NBT NBTTag name
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
            throw new NBTException("NBTTag class " + tagClass.getName() + " not supported yet.");
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
