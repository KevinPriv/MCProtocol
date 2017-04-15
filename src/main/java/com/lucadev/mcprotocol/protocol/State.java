package com.lucadev.mcprotocol.protocol;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum State {

    HANDSHAKE(0),
    STATUS(1),
    LOGIN(2),
    PLAY(3);

    private int stateCode;

    State(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getStateCode() {
        return stateCode;
    }
}
