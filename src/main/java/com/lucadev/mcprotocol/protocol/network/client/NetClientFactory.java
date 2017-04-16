package com.lucadev.mcprotocol.protocol.network.client;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.impl.DefaultNetClientFactory;

/**
 * Factory used to create packet-based networking clients.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see NetClient
 */
public abstract class NetClientFactory {

    private static NetClientFactory defaultFactory;

    /**
     * Create default networking client.
     * @param bot the bot instance.
     * @return newly created networking client.
     */
    public abstract NetClient createClient(Bot bot);

    /**
     * @return lazy initiated singleton instance of the default factory implementation.
     */
    public static NetClientFactory getDefault() {
        if(defaultFactory == null) {
            defaultFactory = new DefaultNetClientFactory();
        }
        return defaultFactory;
    }

}
