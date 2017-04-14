package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.game.Position;
import com.lucadev.mcprotocol.game.world.ChunkSection;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Contains static methods to read variables and stuff
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class VarHelper {

    public static final int MAX_STRING_LENGTH = 32767;
    /**
     * Credit to http://wiki.vg/Protocol
     * @param is
     * @return
     * @throws IOException
     */
    public static int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }
        return i;
    }

    public static Position readPosition(DataInputStream dis) throws IOException {
        Position pos = new Position(dis.readLong());
        return pos;
    }

    public static void writePosition(DataOutputStream dos, Position pos) throws IOException {
        dos.writeLong(pos.encode());
    }

    public static int varIntLength(int x) {
        int size = 0;
        while(true) {
            size++;
            if((x & 0xFFFFFF80) == 0)
                return size;
            x >>>= 7;
        }
    }

    /**
     * Credit to http://wiki.vg/Protocol
     * @param is
     * @return
     * @throws IOException
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
     * Credit to http://wiki.vg/Protocol
     * @param os
     * @param value
     * @throws IOException
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
     * Credit to http://wiki.vg/Protocol
     * @param os
     * @param value
     * @throws IOException
     */
    public static void writeVarLong(DataOutputStream os, long value) throws IOException {
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            os.writeByte(temp);
        } while (value != 0);
    }

    public static String readString(DataInputStream in, int maxSize) throws IOException {
        int length = readVarInt(in);
        if(length > maxSize)
            throw new IOException("String too big");

        byte[] bytes = new byte[length];
        in.readFully(bytes);
        return new String(bytes);
    }

    public static String readString(DataInputStream in) throws IOException {
        return readString(in, MAX_STRING_LENGTH);
    }

    public static void writeString(DataOutputStream out, String string) throws IOException {
        if(string.length() > MAX_STRING_LENGTH)
            throw new IOException("String too big");
        writeVarInt(out, string.length());
        out.writeBytes(string);
    }

}
