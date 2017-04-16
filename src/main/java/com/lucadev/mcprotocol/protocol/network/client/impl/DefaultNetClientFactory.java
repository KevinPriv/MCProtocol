package com.lucadev.mcprotocol.protocol.network.client.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

/**
 * Factory used to create network clients.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultNetClientFactory extends NetClientFactory {

    /**
     * Create default crypto-disabled networking client.
     * @param bot the bot instance.
     * @param connection the connection to wrap around.
     * @return newly created networking client.
     */
    @Override
    public NetClient createClient(Bot bot, Connection connection) {
        return new DefaultNetClient(bot, connection);
    }
}
