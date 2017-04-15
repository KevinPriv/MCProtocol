package com.lucadev.mcprotocol.protocol.pluginchannel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Abstract class to be implemented by plugin channels.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class PluginChannel {

    private final String name;

    /**
     * Construct the plugin channel and specify the channel name.
     * @param name the plugin channel's name.
     */
    public PluginChannel(String name) {
        this.name = name;
    }

    /**
     * @return channel name.
     */
    public String getName() {
        return name;
    }

    /**
     * Handle custom data payload
     * @param is stream containing all payload data.
     * @throws IOException when something goes wrong while handling payload data.
     */
    public abstract void readPayload(DataInputStream is) throws IOException;

    /**
     * When registering your channel to the server write your data payload through this method.
     * @param dos stream for custom data payload.
     * @throws IOException when something goes wrong while writing payload data.
     */
    public abstract void registerToServer(DataOutputStream dos) throws IOException;

}
