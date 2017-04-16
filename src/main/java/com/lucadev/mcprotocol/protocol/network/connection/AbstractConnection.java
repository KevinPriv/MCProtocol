package com.lucadev.mcprotocol.protocol.network.connection;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Implementation of Connection that implements a couple of methods.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractConnection implements Connection {

    private SocketFactory socketFactory;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    /**
     * Constructor which constructs the class to use the default socket factory.
     */
    public AbstractConnection() {
        this(SocketFactory.getDefault());
    }

    /**
     * @param socketFactory socket factory to use when creating a socket for the connection.
     */
    public AbstractConnection(SocketFactory socketFactory) {
        setSocketFactory(socketFactory);
    }

    /**
     * Obtain a Data input stream from the connection
     *
     * @return data stream from the connection.
     */
    @Override
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /**
     * Obtain the output stream from the connection
     *
     * @return data stream from the connection
     */
    @Override
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    /**
     * Get the factory used to create the socket for the connection
     *
     * @return socket factory instance
     */
    @Override
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * Set the SocketFactory we should use when creating a socket.
     *
     * @param socketFactory the socket factory we will be using.
     */
    @Override
    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * @return the raw tcp connection as a socket.
     */
    @Override
    public Socket getSocket() {
        return socket;
    }

    /**
     * Sets the connection's socket. Only accessable through child classes.
     * May only be used if the connection has not connected yet.
     * @param socket the socket to set.
     * @see #isConnected() is used for connection check.
     */
    protected void setSocket(Socket socket) {
        if(isConnected()) {
            throw new IllegalStateException("May not set socket after connection has been established.");
        }
        this.socket = socket;
    }

    /**
     * Sets the input stream for the connection.
     * @param dataInputStream input stream to use.
     */
    protected void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    /**
     * Sets the output stream for the connection
     * @param dataOutputStream output stream to use
     */
    protected void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    /**
     * @return state of the connection, requires all streams and sockets to be connected.
     */
    @Override
    public boolean isConnected() {
        return getSocket() != null && getSocket().isConnected() &&
                getDataInputStream() != null && getDataOutputStream() != null;
    }
}
