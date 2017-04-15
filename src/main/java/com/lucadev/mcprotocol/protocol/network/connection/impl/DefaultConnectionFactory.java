package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;

import javax.net.SocketFactory;

/**
 * Default and simple implementation of a ConnectionFactory
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see ConnectionFactory
 */
public class DefaultConnectionFactory extends ConnectionFactory {

    /**
     * Creates a new connection.
     * @return a new connection.
     */
    public Connection createConnection() {
        return new DefaultConnection();
    }

    /**
     * Create a new connection with the specified socket factory.
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return
     */
    public Connection createConnection(SocketFactory socketFactory) {
        return new DefaultConnection(socketFactory);
    }
}
