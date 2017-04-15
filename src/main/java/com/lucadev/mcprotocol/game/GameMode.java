package com.lucadev.mcprotocol.game;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum GameMode {

    SURVIVAL((byte) 0),
    CREATIVE((byte) 1),
    ADVENTURE((byte) 2),
    SPECTATOR((byte) 3),
    HARDCORE((byte) 0x8);

    private byte flag;

    GameMode(byte flag) {
        this.flag = flag;
    }

    public static GameMode valueOf(byte flag) {
        switch (flag) {
            case 0:
                return SURVIVAL;
            case 1:
                return CREATIVE;
            case 2:
                return ADVENTURE;
            case 3:
                return SPECTATOR;
            case 0x8:
                return HARDCORE;
        }
        return SURVIVAL;//idk maybe change this?
    }
}
