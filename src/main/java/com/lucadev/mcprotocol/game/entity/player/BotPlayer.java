package com.lucadev.mcprotocol.game.entity.player;

import com.lucadev.mcprotocol.bots.AbstractPlayBot;
import com.lucadev.mcprotocol.bots.PlayBot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.game.Dimension;
import com.lucadev.mcprotocol.game.GameMode;
import com.lucadev.mcprotocol.game.PlayerAbilities;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class BotPlayer implements Player {

    private int entityId;
    private GameMode gameMode;
    private Dimension dimension;
    private Difficulty difficulty;
    private String levelType;
    private boolean showReducedDebugInfo;
    private String username;
    private String uuid;
    private PlayerAbilities playerAbilities;
    private double x, y, z;
    private float yaw;
    private float pitch;
    private boolean onGround = true;

    public BotPlayer(int entityId, String uuid, String username, GameMode gameMode, Dimension dimension, Difficulty difficulty, String levelType, boolean showReducedDebugInfo) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.levelType = levelType;
        this.showReducedDebugInfo = showReducedDebugInfo;
        this.username = username;
        this.uuid = uuid;
    }

    /**
     * Register listeners for the packets required to manage the player object.
     * Also contains the tick workers if needed.
     * @param bot minimum bot implementation to handle a player.
     */
    public abstract void registerProtocolListeners(PlayBot bot);

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String getLevelType() {
        return levelType;
    }

    @Override
    public boolean showReducedDebugInfo() {
        return showReducedDebugInfo;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public PlayerAbilities getPlayerAbilities() {
        return playerAbilities;
    }

    @Override
    public void setPlayerAbilities(PlayerAbilities playerAbilities) {
        this.playerAbilities = playerAbilities;
    }

    @Override
    public void setOnGround(boolean onground) {
        this.onGround = onground;
    }

    @Override
    public int getEntityID() {
        return entityId;
    }

    /**
     * Get player username
     *
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Get player uuid
     *
     * @return
     */
    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public double getPosX() {
        return x;
    }

    @Override
    public double getPosY() {
        return y;
    }

    @Override
    public double getPosZ() {
        return z;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setYawPitch(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

}
