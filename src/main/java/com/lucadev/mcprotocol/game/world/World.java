package com.lucadev.mcprotocol.game.world;

import java.util.HashMap;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class World {

    private HashMap<Integer, HashMap<Integer, Chunk>> map = new HashMap<>();

    public Chunk getChunk(int xPos, int zPos) {
        if(!map.containsKey(xPos)) {
            return null;
        }
        HashMap<Integer, Chunk> zMap = map.get(xPos);
        if(!zMap.containsKey(zPos)) {
            return null;
        }

        return zMap.get(zPos);
    }

    public void setChunk(Chunk chunk) {
        if(!map.containsKey(chunk.getPosX())) {
            map.put(chunk.getPosX(), new HashMap<>());
        }
        map.get(chunk.getPosX()).put(chunk.getPosZ(), chunk);
    }

}
