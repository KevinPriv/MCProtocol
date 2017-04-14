package com.lucadev.mcprotocol.network;

import javax.net.SocketFactory;

/**
 * Basic implementation of connection factory.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultConnectionFactory extends ConnectionFactory {

    /**
     * Creates a connection object.
     *
     * @return
     */
    public Connection createConnection() {
        return new DefaultConnection();
    }

    /**
     * Create a new connection with the specified socket factory.
     *
     * @param socketFactory
     * @return
     */
    public Connection createConnection(SocketFactory socketFactory) {
        return new DefaultConnection(socketFactory);
    }
}
