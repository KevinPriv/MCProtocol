package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.protocol.impl.DefaultProtocolFactory;

/**
 * ProtocolFactory makes it easier to find the required protocol implementation to use.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class ProtocolFactory {

    /**
     * Protocol ID of game version 1.11
     */
    public static final int V1_11 = 315;

    /**
     * Protocol ID of game version 1.11.1
     */
    public static final int V1_11_1 = 316;

    /**
     * Protocol ID of game version 1.11.2
     */
    public static final int V1_11_2 = 316;

    /**
     * Latest supported protocol ID
     */
    public static final int LATEST_VERSION = V1_11_2;

    /**
     * Create protocol implementation of the latest supported protocol id.
     * @return protocol implementation of latest supported protocol version.
     */
    public abstract Protocol createProtocol();

    /**
     * Obtain the protocol for a specific version. May be null if not found/supported.
     * @param version protocol ID
     * @return protocol implementation of the given protocol id
     */
    public abstract Protocol createProtocol(int version);

    /**
     * @return default protocol factory
     */
    public static ProtocolFactory getDefault() {
        return new DefaultProtocolFactory();
    }

}
