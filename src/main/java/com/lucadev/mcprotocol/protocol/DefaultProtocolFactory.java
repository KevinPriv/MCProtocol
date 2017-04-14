package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.protocol.impl.v316.Protocol316;

/**
 * Default implementation of the protocol factory.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultProtocolFactory extends ProtocolFactory {

    /**
     * Returns new protocol implementation of the current version.
     *
     * @return
     */
    @Override
    public Protocol getCreateProtocol() {
        return createProtocol(LATEST_VERSION);
    }

    /**
     * Obtain the protocol for a specific version. May be null if not found.
     *
     * @param version
     * @return
     */
    @Override
    public Protocol createProtocol(int version) {
        switch (version) {
            case V1_11_1:
                return new Protocol316();
            default:
                return null;
        }
    }
}
