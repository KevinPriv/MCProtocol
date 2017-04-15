package com.lucadev.mcprotocol.protocol.packets.cbound.play.world.chunk;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.world.Chunk;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C32ChunkData extends AbstractPacket implements ReadablePacket {

    private int xPos;
    private int zPos;
    private boolean continuous;
    //16 block height mask
    private int heightMask;

    @Override
    public int getId() {
        return 0x20;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        /*
        xPos = is.readInt();
        zPos = is.readInt();
        continuous = is.readBoolean();
        Chunk chunk;
        if(continuous) {
            chunk = new Chunk(xPos, zPos);
        } else {
            chunk = bot.getWorld().getChunk(xPos, zPos);
        }
        heightMask = readVarInt(is);
        //check mask to calculate from which height we have information

        int chunkSize = readVarInt(is);
        is.skipBytes(chunkSize);
        readChunkSections(chunk, continuous, heightMask, is, chunkSize);

        byte[] biomeInfo;
        if(continuous) {
            biomeInfo = new byte[256];
            //biome info, 1 per 2d plane location(x,z) (16x16=256 bytes)
            is.readFully(biomeInfo);
        }

        int blockEntitySize = readVarInt(is);
        if(blockEntitySize == 0) {
            return;
        }
        NBTInputStream nbt = new NBTInputStream(is);
        NBTTag[] nbtTags = new NBTTag[blockEntitySize];
        for(int x = 0; x < blockEntitySize; x++) {
            nbtTags[x] = nbt.readTag();
            System.out.println(nbtTags[x].toString());
        }*/
    }

    private void readChunkSections(Chunk chunk, boolean continuous, int heightMask, DataInputStream is, int chunkSize) {

    }
}
