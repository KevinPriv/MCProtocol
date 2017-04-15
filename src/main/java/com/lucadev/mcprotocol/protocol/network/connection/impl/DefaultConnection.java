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
 * Default implementation of Connection uses the default socket factory.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see Connection
 */
public class DefaultConnection implements Connection {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private SocketFactory socketFactory;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    /**
     * Constructor setting the socketfactory to use.
     * @param socketFactory the SocketFactory to use for this connection.
     */
    public DefaultConnection(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * Default constructor setting up with the default socket factory.
     */
    public DefaultConnection() {
        this(SocketFactory.getDefault());
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
        socket = socketFactory.createSocket();
        socket.connect(new InetSocketAddress(host, port));
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Closes the socket and streams immediately.
     * @throws IOException when something goes wrong closing the connection.
     */
    public void close() throws IOException {
        logger.info("Closing streams and socket.");
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

    /**
     * Obtain a Data input stream from the connection
     * @return data stream from the connection.
     */
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /**
     * Obtain the output stream from the connection
     * @return data stream from the connection
     */
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    /**
     * Set the input stream. Should be coming from the same connection(socket, etc...)
     * @param dis data input stream.
     */
    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dataInputStream = dis;
    }

    /**
     * Set the output stream. Should be coming from the same connection(socket, etc...)
     * @param dos data output stream.
     */
    @Override
    public void setDataOutputStream(DataOutputStream dos) {
        this.dataOutputStream = dos;
    }

    /**
     * Get the factory used to create the socket for the connection
     * @return socket factory instance
     */
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * Set the SocketFactory we should use when creating a socket.
     * @param socketFactory the socket factory we will be using.
     */
    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * @return the raw tcp connection as a socket.
     */
    public Socket getSocket() {
        return socket;
    }
}
