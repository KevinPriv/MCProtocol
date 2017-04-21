package com.lucadev.mcprotocol.protocol.network.io;

import com.lucadev.io.Buffer;
import com.lucadev.io.DataBuffer;
import com.lucadev.mcprotocol.protocol.VarHelper;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class VarDataBuffer extends DataBuffer {

    public VarDataBuffer() {
    }

    public VarDataBuffer(Buffer buffer) {
        super(buffer);
    }

    public int readVarInt() throws IOException {
        return VarHelper.readVarInt(getInputStream());
    }

    public void writeVarInt(int v) throws IOException {
        VarHelper.writeVarInt(getOutputStream(), v);
    }

    public long readVarLong() throws IOException {
        return VarHelper.readVarLong(getInputStream());
    }

    public void writeVarLong(long v) throws IOException {
        VarHelper.writeVarLong(getOutputStream(), v);
    }

    public String readVarString(int maxSize) throws IOException {
        return VarHelper.readString(getInputStream(), maxSize);
    }

    public String readVarString() throws IOException {
        return VarHelper.readString(getInputStream());
    }

    public void writeVarString(String str) throws IOException {
        VarHelper.writeString(getOutputStream(), str);
    }

}
