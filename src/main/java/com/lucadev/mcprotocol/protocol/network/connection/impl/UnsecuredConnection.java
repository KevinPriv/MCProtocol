package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.protocol.network.connection.AbstractConnection;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Default implementation of an unsecured Connection uses the default socket factory.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see Connection
 */
public class UnsecuredConnection extends AbstractConnection {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UnsecuredConnection.class);

    /**
     * Constructor which constructs the class to use the default socket factory.
     */
    public UnsecuredConnection() {
        super();
    }

    /**
     * @param socketFactory socket factory to use when creating a socket for the connection.
     */
    public UnsecuredConnection(SocketFactory socketFactory) {
        super(socketFactory);
    }

    /**
     * Connect to a TCP process on the specified host and port.
     *
     * @param host IP address or host address
     * @param port TCP port
     * @throws IOException gets thrown when something goes wrong trying to connect.
     */
    public void connect(String host, int port) throws IOException {
        logger.info("Connecting to {} on port {}", host, port);
        setSocket(getSocketFactory().createSocket());
        getSocket().connect(new InetSocketAddress(host, port));
        setDataInputStream(new DataInputStream(getSocket().getInputStream()));
        setDataOutputStream(new DataOutputStream(getSocket().getOutputStream()));
    }

    /**
     * Closes the socket and streams immediately.
     * @throws IOException when something goes wrong closing the connection.
     */
    public void close() throws IOException {
        logger.info("Closing streams and socket.");
        getDataInputStream().close();
        getDataOutputStream().close();
        getSocket().close();
    }

}
