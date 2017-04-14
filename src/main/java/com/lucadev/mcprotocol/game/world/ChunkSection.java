package com.lucadev.mcprotocol.game.world;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ChunkSection {

    private int bitsPerBlock;

    private int[] palette;

    private long[] data;

    private byte[] blockLight;

    private byte[] skyLight;

    public ChunkSection(int bitsPerBlock, int[] palette, long[] data, byte[] blockLight, byte[] skyLight) {
        this.bitsPerBlock = bitsPerBlock;
        this.palette = palette;
        this.data = data;
        this.blockLight = blockLight;
        this.skyLight = skyLight;
    }

    public int getBitsPerBlock() {
        return bitsPerBlock;
    }

    public int[] getPalette() {
        return palette;
    }

    public long[] getData() {
        return data;
    }

    public byte[] getBlockLight() {
        return blockLight;
    }

    public byte[] getSkyLight() {
        return skyLight;
    }
}
