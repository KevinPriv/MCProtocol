package com.lucadev.mcprotocol.game;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum Dimension {
    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private int flag;

    Dimension(int flag) {
        this.flag = flag;
    }

    public static Dimension valueOf(int flag) {
        switch (flag) {
            case -1:
                return NETHER;
            case 0:
                return OVERWORLD;
            case 1:
                return END;
        }
        return OVERWORLD;
    }
}
