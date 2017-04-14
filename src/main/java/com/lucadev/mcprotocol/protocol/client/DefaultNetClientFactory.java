package com.lucadev.mcprotocol.protocol.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.network.Connection;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultNetClientFactory extends NetClientFactory {

    @Override
    public NetClient createClient(Bot bot, Connection connection) {
        return new SimpleNetClient(bot, connection);
    }
}
