package com.lucadev.mcprotocol.protocol.pluginchannel.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.S09PluginMessage;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannel;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;

/**
 * Default PluginChannelManager implementation.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultPluginChannelManager implements PluginChannelManager {

    private HashMap<String, PluginChannel> map = new HashMap<>();

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultPluginChannelManager.class);

    /**
     * Register a new plugin channel.
     * @param pluginChannel the plugin channel to register.
     */
    @Override
    public void register(PluginChannel pluginChannel) {
        map.put(pluginChannel.getName(), pluginChannel);
    }

    /**
     * Unregister/remove a plugin channel.
     * @param pluginChannel the plugin channel to remove.
     */
    @Override
    public void unregister(PluginChannel pluginChannel) {
        map.remove(pluginChannel.getName());
    }

    /**
     * Get a plugin channel by its name.
     * @param name plugin channel name. Case sensitive.
     * @return corresponding plugin channel or null if not found.
     */
    @Override
    public PluginChannel getPluginChannel(String name) {
        if (!map.containsKey(name)) {
            return null;
        }
        return map.get(name);
    }

    /**
     * Raw handle method to handle incoming plugin messages.
     * @param name plugin channel name.
     * @param data payload for that channel.
     * @throws IOException when something goes wrong handling the channel.
     */
    @Override
    public void handle(String name, byte[] data) throws IOException {
        logger.info("Handling plugin message: " + name);
        PluginChannel pluginChannel = getPluginChannel(name);
        if (pluginChannel == null) {
            logger.info("Plugin channel {} not supported", name);
            return;
        }

        pluginChannel.readPayload(new DataInputStream(new ByteArrayInputStream(data)));
    }

    /**
     * Register the channels to the server.
     * @param bot instance of the bot.
     * @param client net client which is used for packet networking.
     * @see com.lucadev.mcprotocol.protocol.network.client.NetClient
     */
    @Override
    public void registerToServer(Bot bot, NetClient client) {
        for (PluginChannel pluginChannel : map.values()) {
            try {
                ByteArrayOutputStream ba = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(ba);
                pluginChannel.registerToServer(dos);
                dos.close();
                client.sendPacket(new S09PluginMessage(pluginChannel.getName(), ba.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
