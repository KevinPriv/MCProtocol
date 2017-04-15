package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.*;
import com.lucadev.mcprotocol.protocol.network.stream.EndianSwitchableInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

import static com.lucadev.mcprotocol.game.nbt.NBT.*;

/**
 * IO NBT input stream
 *
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

    public NBTTag readTag() throws IOException {
        return readTag(0);
    }

    public NBTTag readTag(int depth) throws IOException {
        int typeId = readByte() & 0xFF;
        if (typeId == getIdByTagClass(NBTEndTag.class)) {
            return new NBTEndTag();
        }

        int nameLength = readShort() & 0xFFFF;
        byte[] nameBytes = new byte[nameLength];
        readFully(nameBytes);
        String name = new String(nameBytes, NBT.CHARSET);
        return createTag(typeId, name);
    }

    private NBTTag createTag(int typeId, String name) throws IOException {
        NBTTag t;
        switch (typeId) {
            case TAG_END:
                return new NBTEndTag();
            case TAG_BYTE:
                t = new NBTByteTag();
                break;
            case TAG_SHORT:
                t = new NBTShortTag();
                break;
            case TAG_INT:
                t = new NBTIntTag();
                break;
            case TAG_LONG:
                t = new NBTLongTag();
                break;
            case TAG_FLOAT:
                t = new NBTFloatTag();
                break;
            case TAG_DOUBLE:
                t = new NBTDoubleTag();
                break;
            case TAG_BYTE_ARRAY:
                t = new NBTByteArrayTag();
                break;
            case TAG_STRING:
                t = new NBTStringTag();
                break;
            case TAG_LIST:
                t = new NBTListTag(readByte());
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
