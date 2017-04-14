package com.lucadev.mcprotocol.protocol.pluginchannel.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.client.NetClient;
import com.lucadev.mcprotocol.protocol.impl.v316.Protocol316;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.S09PluginMessage;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannel;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class SimplePluginChannelManager implements PluginChannelManager {

    private HashMap<String, PluginChannel> map = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SimplePluginChannelManager.class);

    @Override
    public void register(PluginChannel pluginChannel) {
        map.put(pluginChannel.getName(), pluginChannel);
    }

    @Override
    public void unregister(PluginChannel pluginChannel) {
        map.remove(pluginChannel.getName());
    }

    @Override
    public PluginChannel getPluginChannel(String name) {
        if(!map.containsKey(name)) {
            return null;
        }
        return map.get(name);
    }

    @Override
    public void handle(String name, byte[] data) throws IOException {
        logger.info("Handling plugin message: " + name);
        PluginChannel pluginChannel = getPluginChannel(name);
        if(pluginChannel == null) {
            logger.info("Plugin channel {} not supported", name);
            return;
        }

        pluginChannel.handleData(new DataInputStream(new ByteArrayInputStream(data)));
    }

    /**
     * Register your own channels to the server.
     *
     * @param bot
     * @param client
     */
    @Override
    public void registerToServer(Bot bot, NetClient client) {
        for (PluginChannel pluginChannel : map.values()) {
            try {
                ByteArrayOutputStream ba = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(ba);
                pluginChannel.registerToServer(dos);
                dos.close();
                client.writePacket(new S09PluginMessage(pluginChannel.getName(), ba.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
