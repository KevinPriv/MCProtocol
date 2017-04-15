package com.lucadev.mcprotocol.protocol.network.client.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultNetClientFactory extends NetClientFactory {

    @Override
    public NetClient createClient(Bot bot, Connection connection) {
        return new SimpleNetClient(bot, connection);
    }
}
