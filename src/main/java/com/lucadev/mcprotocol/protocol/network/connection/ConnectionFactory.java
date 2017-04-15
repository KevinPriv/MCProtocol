package com.lucadev.mcprotocol.protocol.network.connection;

import com.lucadev.mcprotocol.protocol.network.connection.impl.DefaultConnectionFactory;

import javax.net.SocketFactory;

/**
 * Factory to create Connection objects
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class ConnectionFactory {

    /**
     * Creates a connection object.
     * @return
     */
    public abstract Connection createConnection();

    /**
     * Create a new connection with the specified socket factory.
     * @param socketFactory
     * @return
     */
    public abstract Connection createConnection(SocketFactory socketFactory);

    public static ConnectionFactory getDefault() {
        return new DefaultConnectionFactory();
    }

}
