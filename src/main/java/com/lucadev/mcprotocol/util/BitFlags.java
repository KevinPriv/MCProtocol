package com.lucadev.mcprotocol.util;

/**
 * https://stackoverflow.com/questions/6067411/checking-flag-bits-java
 */
public class BitFlags {
    public static boolean isFlagSet(byte value, byte flags) {
        return (flags & value) == value;
    }

    public static byte setFlag(byte value, byte flags) {
        return (byte) (flags | value);
    }

    public static byte unsetFlag(byte value, byte flags) {
        return (byte) (flags & ~value);
    }
}
