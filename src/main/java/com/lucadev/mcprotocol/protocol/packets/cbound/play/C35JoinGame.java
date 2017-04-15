package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.game.Dimension;
import com.lucadev.mcprotocol.game.GameMode;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C35JoinGame extends AbstractPacket implements ReadablePacket {

    private int entityId;
    private GameMode gameMode;
    private Dimension dimension;
    private Difficulty difficulty;
    private String levelType;
    private boolean reducedDebug;

    @Override
    public int getId() {
        return 0x23;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        entityId = is.readInt();
        gameMode = GameMode.valueOf(is.readByte());
        dimension = Dimension.valueOf(is.readInt());
        difficulty = Difficulty.getDifficulty(is.readByte());
        byte maxPlayers = is.readByte();//ignored
        levelType = readString(is, 16);
        reducedDebug = is.readBoolean();
    }

    public int getEntityId() {
        return entityId;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getLevelType() {
        return levelType;
    }

    public boolean isReducedDebug() {
        return reducedDebug;
    }

    @Override
    public String toString() {
        return "C35JoinGame{" +
                "entityId=" + entityId +
                ", gameMode=" + gameMode +
                ", dimension=" + dimension +
                ", difficulty=" + difficulty +
                ", levelType='" + levelType + '\'' +
                ", reducedDebug=" + reducedDebug +
                '}';
    }
}
