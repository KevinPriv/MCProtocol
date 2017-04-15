package com.lucadev.mcprotocol.protocol;

/**
 * Enum that is used to identify the connection state.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum State {

    /**
     * Handshake state is only used for the first packet sent most of the time.
     */
    HANDSHAKE(0),

    /**
     * Status state is used when fetching server status.
     * An example of this would be fetching MOTD.
     */
    STATUS(1),

    /**
     * Login state is used to handle the login sequence between client and server.
     */
    LOGIN(2),

    /**
     * Play state is the state in which the most packets will be sent such as motion updates, block data etc...
     */
    PLAY(3);

    /**
     * Integer representation of the state. Used when sending or receiving a handshake.
     */
    private int stateCode;

    /**
     * Constructor for the state which sets the integer representation of the state.
     * @param stateCode the integer value linked to the state
     */
    State(int stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * @return integer value of the state.
     */
    public int getStateCode() {
        return stateCode;
    }
}
