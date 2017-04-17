package com.lucadev.mcprotocol.bots;

import java.io.IOException;

/**
 * Interface used by bots to add server join functionality.
 * This functionality means completing a succesful login.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ServerJoinable {

    /**
     * Join the server at the configured host and port
     * @throws IOException thrown when we cannot join the server somehow.
     */
    void joinServer() throws IOException;
}
