package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.Tag;
import com.lucadev.mcprotocol.game.nbt.types.*;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public final class NBT {

    public static final String CHARSET = "UTF-8";
    public static final int TAG_END = 0;
    public static final int TAG_BYTE = 1;
    public static final int TAG_SHORT = 2;
    public static final int TAG_INT = 3;
    public static final int TAG_LONG = 4;
    public static final int TAG_FLOAT = 5;
    public static final int TAG_DOUBLE = 6;
    public static final int TAG_BYTE_ARRAY = 7;
    public static final int TAG_STRING = 8;
    public static final int TAG_LIST = 9;
    public static final int TAG_COMPOUND = 10;
    public static final int TAG_INT_ARRAY = 11;

    private NBT() {

    }

    public static final int getIdByTagClass(Class<? extends Tag> clazz) {
        if(clazz == EndTag.class) {
            return TAG_END;
        } else if (clazz == ByteTag.class) {
            return TAG_BYTE;
        } else if (clazz == ShortTag.class) {
            return TAG_SHORT;
        } else if (clazz == IntTag.class) {
            return TAG_INT;
        } else if (clazz == LongTag.class) {
            return TAG_LONG;
        } else if (clazz == FloatTag.class) {
            return TAG_FLOAT;
        } else if (clazz == DoubleTag.class) {
            return TAG_DOUBLE;
        } else if (clazz == ByteArrayTag.class) {
            return TAG_BYTE_ARRAY;
        } else if (clazz == StringTag.class) {
            return TAG_STRING;
        } else if (clazz == ListTag.class) {
            return TAG_LIST;
        } else if (clazz == CompoundTag.class) {
            return TAG_COMPOUND;
        } else if (clazz == IntArrayTag.class) {
            return TAG_INT_ARRAY;
        }
        return -1;
    }

    public static final Class<? extends Tag> getTagClassById(int tagCode) {
        switch (tagCode) {
            case 0:
                return EndTag.class;
            case 1:
                return ByteTag.class;
            case 2:
                return ShortTag.class;
            case 3:
                return IntTag.class;
            case 4:
                return LongTag.class;
            case 5:
                return FloatTag.class;
            case 6:
                return DoubleTag.class;
            case 7:
                return ByteArrayTag.class;
            case 8:
                return StringTag.class;
            case 9:
                return ListTag.class;
            case 10:
                return CompoundTag.class;
            case 11:
                return IntArrayTag.class;
        }
        return null;
    }
}
