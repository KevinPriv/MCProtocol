package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;

import javax.net.SocketFactory;

/**
 * Factory that returns key securing connections that can be used by the Minecraft protocol.
 * The secure connections can be cast to KeySecuredConnection
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see ConnectionFactory
 * @see KeySecuredConnection
 */
public class KeySecuredConnectionFactory extends ConnectionFactory {

    /**
     * Creates a new connection that cannot be secured.
     *
     * @return a new connection.
     */
    @Override
    public Connection createConnection() {
        return createConnection(SocketFactory.getDefault());
    }

    /**
     * Create a new connection that cannot be secured with the specified socket factory.
     *
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return
     */
    @Override
    public Connection createConnection(SocketFactory socketFactory) {
        return new KeySecuredConnection(socketFactory);
    }

}
