package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.Tag;
import com.lucadev.mcprotocol.game.nbt.types.*;
import com.lucadev.mcprotocol.network.io.EndianSwitchableInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

import static com.lucadev.mcprotocol.game.nbt.NBT.*;

/**
 * IO NBT input stream
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTInputStream extends EndianSwitchableInputStream {

    private boolean compressed;

    public NBTInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false, ByteOrder.BIG_ENDIAN);
    }

    public NBTInputStream(InputStream inputStream, boolean compressed) throws IOException {
        this(inputStream, compressed, ByteOrder.BIG_ENDIAN);
    }

    public NBTInputStream(InputStream inputStream, boolean compressed, ByteOrder byteOrder) throws IOException {
        super(compressed ? new GZIPInputStream(inputStream) : inputStream, byteOrder);
        this.compressed = compressed;
    }

    public Tag readTag() throws IOException {
        return readTag(0);
    }

    public Tag readTag(int depth) throws IOException{
        int typeId = readByte() & 0xFF;
        if(typeId == getIdByTagClass(EndTag.class)) {
            return new EndTag();
        }

        int nameLength = readShort() & 0xFFFF;
        byte[] nameBytes = new byte[nameLength];
        readFully(nameBytes);
        String name = new String(nameBytes, NBT.CHARSET);
        return createTag(typeId, name);
    }

    private Tag createTag(int typeId, String name) throws IOException{
        Tag t;
        switch (typeId) {
            case TAG_END:
                return new EndTag();
            case TAG_BYTE:
                t = new ByteTag();
                break;
            case TAG_SHORT:
                t = new ShortTag();
                break;
            case TAG_INT:
                t = new IntTag();
                break;
            case TAG_LONG:
                t = new LongTag();
                break;
            case TAG_FLOAT:
                t = new FloatTag();
                break;
            case TAG_DOUBLE:
                t = new DoubleTag();
                break;
            case TAG_BYTE_ARRAY:
                t = new ByteArrayTag();
                break;
            case TAG_STRING:
                t = new StringTag();
                break;
            case TAG_LIST:
                t = new ListTag(readByte());
                break;
            default:
                throw new NBTException("Unknown tag id: " + typeId);

        }
        t.setName(name);
        t.readPayload(this);
        return t;
    }

    public boolean isCompressed() {
        return compressed;
    }
}
