package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.game.Position;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Contains static methods to read and write custom datatypes such as VarInt
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class VarHelper {

    /**
     * String size absolute limit for reading and writing.
     */
    public static final int MAX_STRING_LENGTH = 32767;

    /**
     * Reads a VarInt from the given stream.
     * Credit to: http://wiki.vg/Protocol
     *
     * @param in the input stream to read from.
     * @return a VarInt
     * @throws IOException when something goes wrong reading.
     */
    public static int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((k & 0x80) != 128) {
                break;
            }
        }
        return i;
    }

    /**
     * Reads a position datatype from the given stream.
     *
     * @param dis the input to read from.
     * @return the read position.
     * @throws IOException when something goes wrong reading.
     */
    public static Position readPosition(DataInputStream dis) throws IOException {
        return new Position(dis.readLong());
    }

    /**
     * Writes a position datatype to the given stream.
     *
     * @param dos the stream to write the position to.
     * @param pos the position to write.
     * @throws IOException when something goes wrong writing.
     */
    public static void writePosition(DataOutputStream dos, Position pos) throws IOException {
        dos.writeLong(pos.encode());
    }

    /**
     * Get the length of a VarInt which can be useful for some packet structures and compression.
     *
     * @param x a VarInt
     * @return length of the given VarInt
     */
    public static int varIntLength(int x) {
        int size = 0;
        while (true) {
            size++;
            if ((x & 0xFFFFFF80) == 0) {
                return size;
            }
            x >>>= 7;
        }
    }

    /**
     * Read a VarLong from the given stream.
     * Credit to http://wiki.vg/Protocol
     *
     * @param is the input stream to read the VarLong from
     * @return a VarLong
     * @throws IOException when something goes wrong reading.
     */
    public static long readVarLong(DataInputStream is) throws IOException {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = is.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    /**
     * Writes a VarInt to the specified output stream
     * Credit: http://wiki.vg/Protocol
     *
     * @param out      the stream to write to.
     * @param paramInt the VarInt
     * @throws IOException gets thrown when something goes wrong writing.
     */
    public static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }

            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }

    /**
     * Writes a VarLong to the specified output stream.
     * Credit: http://wiki.vg/Protocol
     *
     * @param os    the stream to write to.
     * @param value the VarLong
     * @throws IOException gets thrown when something goes wrong writing.
     */
    public static void writeVarLong(DataOutputStream os, long value) throws IOException {
        do {
            byte temp = (byte) (value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            os.writeByte(temp);
        } while (value != 0);
    }

    /**
     * Read a String datatype from the specified stream.
     *
     * @param in      the stream to read from.
     * @param maxSize maximum string size to read.
     * @return the read string
     * @throws IOException when something goes wrong reading.
     */
    public static String readString(DataInputStream in, int maxSize) throws IOException {
        int length = readVarInt(in);
        if (length > maxSize) {
            throw new IOException("String too big");
        }

        byte[] bytes = new byte[length];
        in.readFully(bytes);
        return new String(bytes);
    }

    /**
     * Nearly identical to the other readString but this method uses the max string length and not a specified length.
     *
     * @param in the stream to read from.
     * @return a UTF-8 string.
     * @throws IOException when something goes wrong reading.
     */
    public static String readString(DataInputStream in) throws IOException {
        return readString(in, MAX_STRING_LENGTH);
    }

    /**
     * Write a string to the stream.
     *
     * @param out    the stream to write to.
     * @param string the string to write.
     * @throws IOException gets thrown when something goes wrong writing.
     */
    public static void writeString(DataOutputStream out, String string) throws IOException {
        if (string.length() > MAX_STRING_LENGTH) {
            throw new IOException("String too big");
        }
        writeVarInt(out, string.length());
        out.writeBytes(string);
    }

}
