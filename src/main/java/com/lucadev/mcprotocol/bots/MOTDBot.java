package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.util.model.MOTDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Bot that can fetch server MOTD's
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class MOTDBot extends AbstractBot {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(MOTDBot.class);


    /**
     * Construct a new bots using the given config.
     * Requests a secure connection when setting up the connection object.
     *
     * @param botBuilder the builder contains all required config to setup the bots.
     */
    public MOTDBot(BotBuilder botBuilder) {
        super(botBuilder);
    }

    /**
     * Obtain the server's MOTD and disconnects afterwards. Should only function if serverLogin has not been called/we aren't logged in.
     * Automatically disconnects once the motd has been received.
     *
     * @return server MOTD response in a POJO
     * @throws IOException thrown if we fail to obtain the MOTD
     */
    public MOTDResponse getMOTD() throws IOException {
        MOTDResponse motd = getProtocol().getMOTD();
        disconnect();
        logger.info("Fetched server MOTD");
        return motd;
    }

    /**
     * Obtain the server's MOTD and disconnects afterwards. Should only function if serverLogin has not been called/we aren't logged in.
     *
     * @param host hostname or ip of the server
     * @param port portnumber the server is listening on.
     * @return server MOTD response in a POJO
     * @throws IOException thrown if we fail to obtain the MOTD
     */
    public MOTDResponse getMOTD(String host, int port) throws IOException {
        if(isConnected()) {
            logger.info("Bot still connected. Disconnecting and trying again.");
            disconnect();
        }
        //same connection objects can be reused when connecting
        connect(host, port);
        MOTDResponse motd = getProtocol().getMOTD();
        disconnect();
        logger.info("Fetched server MOTD");
        return motd;
    }

    /**
     * Setup all connection objects and opens the streams.
     * The configured bots address and port from the builder are used.
     *
     * @throws IOException when something goes wrong while connecting.
     */
    @Override
    public void connect() throws IOException {
        connect(getBotBuilder().getHost(), getBotBuilder().getPort());
    }

    /**
     * Same as the connect method but here you may specify the hostname and port yourself.
     *
     * @param host hostname/ip address
     * @param port port number that the server is listening on.
     * @throws IOException when we could not open the connection.
     */
    @Override
    public void connect(String host, int port) throws IOException {
        if(isConnected()) {
            throw new IllegalStateException("May not connect when already connected.");
        }
        getConnection().connect(host, port);
    }

    /**
     * Disconnect the current connection and streams.
     * Implementations may also put protocol calls in this.
     *
     * @throws IOException when something goes wrong disconnecting.
     */
    @Override
    public void disconnect() throws IOException {
        getProtocol().disconnect();
    }
}
