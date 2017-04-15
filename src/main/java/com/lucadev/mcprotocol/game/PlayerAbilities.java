package com.lucadev.mcprotocol.game;

import com.lucadev.mcprotocol.util.BitFlagUtil;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class PlayerAbilities {

    private boolean invulnerable;
    private boolean flying;
    private boolean flyingAllowed;
    private boolean creativeMode;
    private float flyingSpeed;
    private float fovModifier;

    public PlayerAbilities(byte flag, float flyingSpeed, float fovModifier) {
        this.flyingSpeed = flyingSpeed;
        this.fovModifier = fovModifier;

        invulnerable = BitFlagUtil.isFlagSet(flag, (byte) 0x01);
        flying = BitFlagUtil.isFlagSet(flag, (byte) 0x02);
        flyingAllowed = BitFlagUtil.isFlagSet(flag, (byte) 0x04);
        creativeMode = BitFlagUtil.isFlagSet(flag, (byte) 0x08);
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isFlyingAllowed() {
        return flyingAllowed;
    }

    public boolean isCreativeMode() {
        return creativeMode;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getFovModifier() {
        return fovModifier;
    }

    @Override
    public String toString() {
        return "PlayerAbilities{" +
                "invulnerable=" + invulnerable +
                ", flying=" + flying +
                ", flyingAllowed=" + flyingAllowed +
                ", creativeMode=" + creativeMode +
                ", flyingSpeed=" + flyingSpeed +
                ", fovModifier=" + fovModifier +
                '}';
    }
}
