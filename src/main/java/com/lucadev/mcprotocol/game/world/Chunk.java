package com.lucadev.mcprotocol.game.world;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Chunk {

    private ChunkSection[] sections = new ChunkSection[16];
    private int posX;
    private int posZ;

    public Chunk(int x, int z) {
        this.posX = x;
        this.posZ = z;
    }

    public Chunk(ChunkSection[] chunkSections) {
        if (chunkSections.length != 16) {
            throw new IllegalStateException("Chunk column must be given 16 chunk sections");
        }
        this.sections = chunkSections;
    }

    public ChunkSection[] getSections() {
        return sections;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosZ() {
        return posZ;
    }
}
