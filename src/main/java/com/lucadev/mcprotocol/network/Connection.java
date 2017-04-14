package com.lucadev.mcprotocol.network;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The Connection interface provides a modular approach on creating an IO connection.
 * This connection does and should not implement any MC protocol specific features.
 * It should only open or close data streams and provide them in their respective getters and setters.
 *
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Connection {

    /**
     * Connect to a TCP proces on the specified host and port.
     * @param host IP address or host address
     * @param port TCP port
     * @throws IOException
     */
    void connect(String host, int port) throws IOException;

    /**
     * Closes the socket.
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * Obtain a Data input stream from the connection
     * @return
     */
    DataInputStream getDataInputStream();

    /**
     * Obtain the output stream from the connection
     * @return
     */
    DataOutputStream getDataOutputStream();

    void setDataInputStream(DataInputStream dis);

    void setDataOutputStream(DataOutputStream dos);

    /**
     * Obtain an instance of the SocketFactory that is used.
     * @return
     */
    SocketFactory getSocketFactory();

    /**
     * Set the SocketFactory we should use when connecting.
     * @param socketFactory
     */
    void setSocketFactory(SocketFactory socketFactory);

    /**
     * Obtain an instance of the raw TCP socket.
     * @return
     */
    Socket getSocket();
}
