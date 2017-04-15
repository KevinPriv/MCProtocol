package com.lucadev.mcprotocol.game.entity.player;

import com.lucadev.mcprotocol.game.entity.Entity;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface OtherPlayer extends Entity {

    /**
     * Get player username
     * @return
     */
    String getUsername();

    /**
     * Get player uuid
     * @return
     */
    String getUUID();

    double getPosX();

    double getPosY();

    double getPosZ();

    float getYaw();

    float getPitch();

    void setPosition(double x, double y, double z);

    void setYawPitch(float yaw, float pitch);

    boolean isOnGround();
}
