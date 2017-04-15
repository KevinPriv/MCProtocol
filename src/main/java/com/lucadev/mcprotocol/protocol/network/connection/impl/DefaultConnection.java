package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Default implementation of Connection which simply uses the default socketfactory
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultConnection implements Connection {

    /**
     * SLF4J logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    /* Standard java IO classes */
    private SocketFactory socketFactory;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public DefaultConnection(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public DefaultConnection() {
        this(SocketFactory.getDefault());
    }

    /**
     * Connect to a TCP proces on the specified host and port.
     *
     * @param host IP address or host address
     * @param port TCP port
     * @throws IOException
     */
    public void connect(String host, int port) throws IOException {
        logger.info("Connecting to {} on port {}", host, port);
        socket = socketFactory.createSocket();
        socket.connect(new InetSocketAddress(host, port));
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Closes the socket after closing the streams.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        logger.info("Closing streams and socket.");
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

    /**
     * Obtain a Data input stream from the connection
     *
     * @return
     */
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /**
     * Obtain the output stream from the connection
     *
     * @return
     */
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dataInputStream = dis;
    }

    @Override
    public void setDataOutputStream(DataOutputStream dos) {
        this.dataOutputStream = dos;
    }

    /**
     * Obtain an instance of the SocketFactory that is used.
     *
     * @return
     */
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * Set the SocketFactory we should use when connecting.
     *
     * @param socketFactory
     */
    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * Obtain an instance of the raw TCP socket.
     *
     * @return
     */
    public Socket getSocket() {
        return socket;
    }
}
