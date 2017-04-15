package com.lucadev.mcprotocol.protocol.network.stream;

import java.io.*;
import java.nio.ByteOrder;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class EndianSwitchableInputStream extends FilterInputStream implements DataInput {

    private ByteOrder byteOrder;

    public EndianSwitchableInputStream(InputStream inputStream, ByteOrder byteOrder) {
        super(inputStream instanceof DataInputStream ? inputStream : new DataInputStream(inputStream));
        this.byteOrder = byteOrder;
    }

    private DataInputStream getParent() {
        return (DataInputStream) super.in;
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    @Override
    public void readFully(byte[] bytes) throws IOException {
        getParent().readFully(bytes);
    }

    @Override
    public void readFully(byte[] bytes, int i, int i1) throws IOException {
        getParent().readFully(bytes, i, i1);
    }

    @Override
    public int skipBytes(int i) throws IOException {
        return getParent().skipBytes(i);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return getParent().readBoolean();
    }

    @Override
    public byte readByte() throws IOException {
        return getParent().readByte();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return getParent().readUnsignedByte();
    }

    @Override
    public short readShort() throws IOException {
        short ret = getParent().readShort();
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            ret = Short.reverseBytes(ret);
        }
        return ret;
    }

    @Override
    public int readUnsignedShort() throws IOException {
        int ret = getParent().readUnsignedShort();
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            ret = (char) (Integer.reverseBytes(ret) >> 16);
        }
        return ret;
    }

    @Override
    public char readChar() throws IOException {
        char ret = getParent().readChar();
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            ret = Character.reverseBytes(ret);
        }
        return ret;
    }

    @Override
    public int readInt() throws IOException {
        int ret = getParent().readInt();
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            ret = Integer.reverseBytes(ret);
        }
        return ret;
    }

    @Override
    public long readLong() throws IOException {
        long ret = getParent().readLong();
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            ret = Long.reverseBytes(ret);
        }
        return ret;
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());//endian is handled in readInt already
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());//endian is handled in readLong already
    }

    @Deprecated
    @Override
    public String readLine() throws IOException {
        return getParent().readLine();
    }

    @Override
    public String readUTF() throws IOException {
        return getParent().readUTF();
    }
}
