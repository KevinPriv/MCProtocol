package com.lucadev.mcprotocol.protocol.network.connection;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The Connection interface provides a modular approach on creating an IO connection.
 * This connection does and should not implement any MC protocol specific features.
 * It should only open or disconnect data streams and provide them in their respective getters and setters.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Connection {

    /**
     * Connect to a TCP process on the specified host and port.
     *
     * @param host IP address or host address
     * @param port TCP port
     * @throws IOException gets thrown when something goes wrong trying to connect.
     */
    void connect(String host, int port) throws IOException;

    /**
     * Closes the socket and streams immediately.
     * @throws IOException when something goes wrong closing the connection.
     */
    void close() throws IOException;

    /**
     * Obtain a Data input stream from the connection
     * @return data stream from the connection.
     */
    DataInputStream getDataInputStream();

    /**
     * Obtain the output stream from the connection
     * @return data stream from the connection
     */
    DataOutputStream getDataOutputStream();

    /**
     * Get the factory used to create the socket for the connection
     * @return socket factory instance
     */
    SocketFactory getSocketFactory();

    /**
     * Set the SocketFactory we should use when creating a socket.
     * @param socketFactory the socket factory we will be using.
     */
    void setSocketFactory(SocketFactory socketFactory);

    /**
     * @return the raw tcp connection as a socket.
     */
    Socket getSocket();

    /**
     * @return state of the connection, requires all streams and sockets to be connected.
     */
    boolean isConnected();
}
