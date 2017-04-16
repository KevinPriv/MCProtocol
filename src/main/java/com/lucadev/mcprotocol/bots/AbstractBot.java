package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract implementation of a bots which implements some basic functionality each bots will have.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractBot implements Bot {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractBot.class);

    private final BotBuilder botBuilder;
    private Protocol protocol;
    private Connection connection;
    private NetClient netClient;

    /**
     * Construct a new bots using the given config.
     * Requests a secure connection when setting up the connection object.
     * @param botBuilder the builder contains all required config to setup the bots.
     */
    public AbstractBot(BotBuilder botBuilder) {
        this.botBuilder = botBuilder;
        //Create protocol from builder config.
        protocol = botBuilder.getProtocolFactory().createProtocol(botBuilder.getProtocolVersion());
        protocol.setup(this);
        connection = protocol.getConnectionFactory().createSecureConnection(botBuilder.getSocketFactory());
        //netClient comes after connection since NetClient depends heavily on Connection
        netClient = protocol.getNetClientFactory().createClient(this);

    }

    /**
     * @return Builder that was used to configure the bots.
     */
    @Override
    public BotBuilder getBotBuilder() {
        return botBuilder;
    }

    /**
     * @return Protocol implementation to follow
     */
    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Basic implementation that checks the Connection object
     * @return true if connection is not null and the Connection#isConnected
     * @see Connection
     */
    @Override
    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    /**
     * @return tcp connection and streams currently being used for the connection.
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * @return packet-based network client used to communicate to the server.
     */
    @Override
    public NetClient getNetClient() {
        return netClient;
    }

    /**
     * Sets the protocol implementation that is followed. May not be changed once connected
     * @param protocol implementation of the protocol.
     */
    public void setProtocol(Protocol protocol) {
        if(isConnected()) {
            throw new IllegalStateException("Cannot switch protocols while bots is connected.");
        }
        this.protocol = protocol;
    }

    /**
     * Set the connection class to use. NetClient should also be updated afterwards to prevent possible crashes/unwanted behaviour.
     * @param connection connection to use.
     * @see #setNetClient(NetClient)
     */
    public void setConnection(Connection connection) {
        if(isConnected()) {
            throw new IllegalStateException("Cannot switch connection while bots is connected.");
        }
        this.connection = connection;
    }

    /**
     * Update the net client that is in use. Can only change when the bots is not connected.
     * Recommended to use the NetClient factory found in the Protocol class to prevent any protocol related crashes.
     * @param netClient new NetClient class to use.
     * @see Protocol
     */
    public void setNetClient(NetClient netClient) {
        if(isConnected()) {
            throw new IllegalStateException("Cannot switch net client while bots is connected.");
        }
        this.netClient = netClient;
    }
}
