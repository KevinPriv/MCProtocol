package com.lucadev.mcprotocol.protocol.network.client.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;

/**
 * Factory used to create network clients.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultNetClientFactory extends NetClientFactory {

    /**
     * Create default networking client.
     * @param bot the bot instance.
     * @return newly created networking client.
     */
    @Override
    public NetClient createClient(Bot bot) {
        return new DefaultNetClient(bot);
    }
}
