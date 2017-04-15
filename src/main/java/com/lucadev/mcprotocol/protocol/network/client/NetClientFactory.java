package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.impl.DefaultNetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class NetClientFactory {

    public abstract NetClient createClient(Bot bot, Connection connection);

    public static NetClientFactory getDefault() {
        return new DefaultNetClientFactory();
    }

}
