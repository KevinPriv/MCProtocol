package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.impl.DefaultNetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

/**
 * Factory used to create packet-based networking clients.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see NetClient
 */
public abstract class NetClientFactory {

    /**
     * Create default networking client.
     * @param bot the bot instance.
     * @param connection the connection to wrap around.
     * @return newly created networking client.
     */
    public abstract NetClient createClient(Bot bot, Connection connection);

    /**
     * @return default factory implementation.
     */
    public static NetClientFactory getDefault() {
        return new DefaultNetClientFactory();
    }

}
