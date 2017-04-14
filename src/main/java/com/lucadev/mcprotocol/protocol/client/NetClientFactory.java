package com.lucadev.mcprotocol.protocol.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.network.Connection;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class NetClientFactory {

    public abstract NetClient createClient(Bot bot, Connection connection);

    public static NetClientFactory getDefault() {
        return new DefaultNetClientFactory();
    }

}
