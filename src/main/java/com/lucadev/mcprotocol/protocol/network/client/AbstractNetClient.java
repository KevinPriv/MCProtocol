package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

/**
 * Abstract net client which implements some basic net client functionality.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractNetClient implements NetClient {

    private final Bot bot;

    /**
     * Constructor initializing the NetClient with a bot instance.
     * @param bot instance of our bot.
     */
    public AbstractNetClient(Bot bot) {
        this.bot = bot;
    }

    /**
     * @return connection to wrap around.
     */
    protected Connection getConnection() {
        return bot.getConnection();
    }

    /**
     * @return instance of the bot.
     */
    protected Bot getBot() {
        return bot;
    }

}
