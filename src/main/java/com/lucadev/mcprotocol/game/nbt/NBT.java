package com.lucadev.mcprotocol.game.nbt;

import com.lucadev.mcprotocol.game.nbt.tags.*;

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

    public static final int getIdByTagClass(Class<? extends NBTTag> clazz) {
        if (clazz == NBTEndTag.class) {
            return TAG_END;
        } else if (clazz == NBTByteTag.class) {
            return TAG_BYTE;
        } else if (clazz == NBTShortTag.class) {
            return TAG_SHORT;
        } else if (clazz == NBTIntTag.class) {
            return TAG_INT;
        } else if (clazz == NBTLongTag.class) {
            return TAG_LONG;
        } else if (clazz == NBTFloatTag.class) {
            return TAG_FLOAT;
        } else if (clazz == NBTDoubleTag.class) {
            return TAG_DOUBLE;
        } else if (clazz == NBTByteArrayTag.class) {
            return TAG_BYTE_ARRAY;
        } else if (clazz == NBTStringTag.class) {
            return TAG_STRING;
        } else if (clazz == NBTListTag.class) {
            return TAG_LIST;
        } else if (clazz == NBTCompoundTag.class) {
            return TAG_COMPOUND;
        } else if (clazz == NBTIntArrayTag.class) {
            return TAG_INT_ARRAY;
        }
        return -1;
    }

    public static final Class<? extends NBTTag> getTagClassById(int tagCode) {
        switch (tagCode) {
            case 0:
                return NBTEndTag.class;
            case 1:
                return NBTByteTag.class;
            case 2:
                return NBTShortTag.class;
            case 3:
                return NBTIntTag.class;
            case 4:
                return NBTLongTag.class;
            case 5:
                return NBTFloatTag.class;
            case 6:
                return NBTDoubleTag.class;
            case 7:
                return NBTByteArrayTag.class;
            case 8:
                return NBTStringTag.class;
            case 9:
                return NBTListTag.class;
            case 10:
                return NBTCompoundTag.class;
            case 11:
                return NBTIntArrayTag.class;
        }
        return null;
    }
}
