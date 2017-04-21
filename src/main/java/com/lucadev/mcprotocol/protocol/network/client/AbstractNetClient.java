package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

/**
 * Abstract net client which implements some basic net client functionality.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractNetClient implements NetClient {

    private final Bot bot;

    /**
     * Constructor initializing the NetClient with a bots instance.
     *
     * @param bot instance of our bots.
     */
    public AbstractNetClient(Bot bot) {
        if (bot == null) {
            throw new NullPointerException("Parameter bot may not be null.");
        }
        this.bot = bot;
    }

    /**
     * Force write packet to the connection.
     *
     * @param packet packet to write to the connection.
     * @throws IOException when something goes wrong while writing the packet to the connection.
     */
    protected abstract void writePacket(WritablePacket packet) throws IOException;

    /**
     * @return connection to wrap around.
     */
    protected Connection getConnection() {
        return bot.getConnection();
    }

    /**
     * @return instance of the bots.
     */
    protected Bot getBot() {
        return bot;
    }

}
