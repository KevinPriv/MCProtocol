package com.lucadev.mcprotocol.protocol.network.connection;

import com.lucadev.mcprotocol.protocol.network.connection.impl.KeySecuredConnectionFactory;

import javax.net.SocketFactory;

/**
 * Factory to create Connection objects.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class ConnectionFactory {

    private static ConnectionFactory defaultFactory;

    /**
     * Creates a new connection that cannot be secured.
     * @return a new connection.
     */
    public abstract Connection createConnection();

    /**
     * Create a new connection with the specified socket factory.
     * @param socketFactory the socketfactory to use for the connection being created.
     * @return
     */
    public abstract Connection createConnection(SocketFactory socketFactory);


    /**
     * Get the default factory used for making connections.
     * In this case it's a factory used to return connections that can be used by the minecraft protocol.
     * @return default connection factory as a singleton instance.
     */
    public static ConnectionFactory getDefault() {
        if(defaultFactory == null) {
            defaultFactory = new KeySecuredConnectionFactory();
        }
        return defaultFactory;
    }

}
