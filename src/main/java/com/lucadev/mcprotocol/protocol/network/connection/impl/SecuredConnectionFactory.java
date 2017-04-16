package com.lucadev.mcprotocol.protocol.network.connection.impl;

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
public class SecuredConnectionFactory extends ConnectionFactory<SecuredConnection> {

    /**
     * Creates a new connection that cannot be secured.
     * @return a new connection.
     */
    public SecuredConnection createConnection() {
        return createConnection(SocketFactory.getDefault());
    }

    /**
     * Create a new connection that cannot be secured with the specified socket factory.
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return
     */
    public SecuredConnection createConnection(SocketFactory socketFactory) {
        return new KeySecuredConnection(socketFactory);
    }

}
