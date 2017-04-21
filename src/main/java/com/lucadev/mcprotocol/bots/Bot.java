package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Bot {

    /**
     * @return Builder that was used to configure the bots.
     */
    BotBuilder getBotBuilder();

    /**
     * @return Protocol implementation to follow
     */
    Protocol getProtocol();

    /**
     * Setup all connection objects and opens the streams.
     * The configured bots address and port from the builder are used.
     *
     * @throws IOException when something goes wrong while connecting.
     */
    void connect() throws IOException;

    /**
     * Same as the connect method but here you may specify the hostname and port yourself.
     *
     * @param host hostname/ip address
     * @param port port number that the server is listening on.
     * @throws IOException when we could not open the connection.
     */
    void connect(String host, int port) throws IOException;

    /**
     * Disconnect the current connection and streams.
     * Implementations may also put protocol calls in this.
     *
     * @throws IOException when something goes wrong disconnecting.
     */
    void disconnect() throws IOException;

    /**
     * @return if the bots is currently connected
     */
    boolean isConnected();

    /**
     * @return tcp connection and streams currently being used for the connection.
     */
    Connection getConnection();

    /**
     * @return packet-based network client used to communicate to the server.
     */
    NetClient getNetClient();
}
