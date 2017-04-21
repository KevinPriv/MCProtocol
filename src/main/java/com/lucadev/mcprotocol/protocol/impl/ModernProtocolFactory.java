package com.lucadev.mcprotocol.protocol.impl;

import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.ProtocolFactory;
import com.lucadev.mcprotocol.protocol.impl.v316.Protocol316;

/**
 * Protocol factory implementation for modern minecraft versions.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see ProtocolFactory
 */
public class ModernProtocolFactory extends ProtocolFactory {

    /**
     * Create protocol implementation of the latest supported protocol id.
     *
     * @return protocol implementation of latest supported protocol protocolVersion.
     */
    @Override
    public Protocol createProtocol() {
        return createProtocol(LATEST_VERSION);
    }

    /**
     * Obtain the protocol for a specific protocolVersion. May be null if not found/supported.
     *
     * @param version protocol ID
     * @return protocol implementation of the given protocol id
     */
    @Override
    public Protocol createProtocol(int version) {
        switch (version) {
            case V1_11_1/*V1_11_1 is equal to V1_11_2 so we can ignore that*/:
                return new Protocol316();
            default:
                return null;
        }
    }
}
