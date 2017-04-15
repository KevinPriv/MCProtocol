package com.lucadev.mcprotocol.protocol.pluginchannel;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;

import java.io.IOException;

/**
 * Interface that specifies the minimal requirements for PluginChannel manager.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannel
 */
public interface PluginChannelManager {

    /**
     * Register a new plugin channel.
     * @param pluginChannel the plugin channel to register.
     */
    void register(PluginChannel pluginChannel);

    /**
     * Unregister/remove a plugin channel.
     * @param pluginChannel the plugin channel to remove.
     */
    void unregister(PluginChannel pluginChannel);

    /**
     * Get a plugin channel by its name.
     * @param name plugin channel name. Case sensitive.
     * @return corresponding plugin channel or null if not found.
     */
    PluginChannel getPluginChannel(String name);

    /**
     * Raw handle method to handle incoming plugin messages.
     * @param name plugin channel name.
     * @param data payload for that channel.
     * @throws IOException when something goes wrong handling the channel.
     */
    void handle(String name, byte[] data) throws IOException;

    /**
     * Register the channels to the server.
     * @param bot instance of the bot.
     * @param client net client which is used for packet networking.
     * @see com.lucadev.mcprotocol.protocol.network.client.NetClient
     */
    void registerToServer(Bot bot, NetClient client);
}
