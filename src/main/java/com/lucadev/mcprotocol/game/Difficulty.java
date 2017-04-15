package com.lucadev.mcprotocol.game;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum Difficulty {

    PEACEFUL((byte) 0),
    EASY((byte) 1),
    NORMAL((byte) 2),
    HARD((byte) 3);

    private byte level;

    Difficulty(byte level) {
        this.level = level;
    }

    public static Difficulty getDifficulty(byte b) {
        for (Difficulty difficulty : values()) {
            if (difficulty.level == b) {
                return difficulty;
            }
        }
        return Difficulty.PEACEFUL;
    }
}
