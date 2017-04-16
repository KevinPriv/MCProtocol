package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;
import com.lucadev.mcprotocol.protocol.network.connection.SecuredConnection;

import javax.net.SocketFactory;

/**
 * Factory that returns key securing connections that can be used by the Minecraft protocol.
 * The secure connections can be cast to KeySecuredConnection
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see ConnectionFactory
 * @see KeySecuredConnection
 */
public class MCConnectionFactory extends ConnectionFactory {

    /**
     * Creates a new connection that cannot be secured.
     * @return a new connection.
     */
    public Connection createConnection() {
        return new UnsecuredConnection();
    }

    /**
     * Create a new connection that cannot be secured with the specified socket factory.
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return
     */
    public Connection createConnection(SocketFactory socketFactory) {
        return new UnsecuredConnection(socketFactory);
    }

    /**
     * Creates a new connection that can be secured.
     * Also makes use of the default socket factory.
     * @return a new connection that can be secured.
     */
    @Override
    public SecuredConnection createSecureConnection() {
        return createSecureConnection(SocketFactory.getDefault());
    }

    /**
     * Create a new connection that can be secured with the specified socket factory.
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return a new connection that will use the specified socket factory.
     */
    @Override
    public SecuredConnection createSecureConnection(SocketFactory socketFactory) {
        return new KeySecuredConnection(socketFactory);
    }
}
