package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.protocol.impl.DefaultProtocolFactory;

/**
 * ProtocolFactory makes it easier to find the required protocol version.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class ProtocolFactory {

    public static final int V1_11 = 315;
    public static final int V1_11_1 = 316;
    public static final int V1_11_2 = 316;

    /**
     * Latest version that is supported
     */
    public static final int LATEST_VERSION = V1_11_2;

    /**
     * Returns new protocol implementation of the current version.
     *
     * @return
     */
    public abstract Protocol getCreateProtocol();

    /**
     * Obtain the protocol for a specific version. May be null if not found.
     *
     * @param version
     * @return
     */
    public abstract Protocol createProtocol(int version);

    /**
     * Returns default factory
     *
     * @return
     */
    public static ProtocolFactory getDefault() {
        return new DefaultProtocolFactory();
    }

}
