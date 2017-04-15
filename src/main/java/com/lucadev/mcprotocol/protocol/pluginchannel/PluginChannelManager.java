package com.lucadev.mcprotocol.protocol.pluginchannel;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface PluginChannelManager {

    void register(PluginChannel pluginChannel);

    void unregister(PluginChannel pluginChannel);

    PluginChannel getPluginChannel(String name);

    void handle(String name, byte[] data) throws IOException;

    /**
     * Register your own channels to the server.
     *
     * @param bot
     * @param client
     */
    void registerToServer(Bot bot, NetClient client);
}
