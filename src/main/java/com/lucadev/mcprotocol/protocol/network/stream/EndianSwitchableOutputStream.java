package com.lucadev.mcprotocol.protocol.network.stream;

import java.io.*;
import java.nio.ByteOrder;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class EndianSwitchableOutputStream extends FilterOutputStream implements DataOutput {

    private ByteOrder byteOrder;

    public EndianSwitchableOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
        super(outputStream instanceof DataOutputStream ? outputStream : new DataOutputStream(outputStream));
        setByteOrder(byteOrder);
    }

    private DataOutputStream getParent() {
        return (DataOutputStream) super.out;
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }


    @Override
    public void writeBoolean(boolean b) throws IOException {
        getParent().writeBoolean(b);
    }

    @Override
    public void writeByte(int i) throws IOException {
        getParent().writeByte(i);
    }

    @Override
    public void writeShort(int i) throws IOException {
        if(byteOrder == ByteOrder.LITTLE_ENDIAN) {
            i = Integer.reverseBytes(i) >> 16;
        }
        getParent().writeShort(i);
    }

    @Override
    public void writeChar(int i) throws IOException {
        if(byteOrder == ByteOrder.LITTLE_ENDIAN) {
            i = Character.reverseBytes((char)i);
        }
        getParent().writeChar(i);
    }

    @Override
    public void writeInt(int i) throws IOException {
        if(byteOrder == ByteOrder.LITTLE_ENDIAN) {
            i = Integer.reverseBytes(i);
        }
        getParent().writeInt(i);
    }

    @Override
    public void writeLong(long l) throws IOException {
        if(byteOrder == ByteOrder.LITTLE_ENDIAN) {
            l = Long.reverseBytes(l);
        }
        getParent().writeLong(l);
    }

    @Override
    public void writeFloat(float v) throws IOException {
        int bits = Float.floatToIntBits(v);
        writeInt(bits);
    }

    @Override
    public void writeDouble(double v) throws IOException {
        long bits = Double.doubleToLongBits(v);
        writeLong(bits);
    }

    @Override
    public void writeBytes(String s) throws IOException {
        getParent().writeBytes(s);
    }

    @Override
    public void writeChars(String s) throws IOException {
        getParent().writeChars(s);
    }

    @Override
    public void writeUTF(String s) throws IOException {
        getParent().writeUTF(s);
    }
}
