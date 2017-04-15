package com.lucadev.mcprotocol.protocol.pluginchannel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class PluginChannel {

    /**
     * Channel name
     */
    private String name;

    /**
     * Constructor setting the channel name
     *
     * @param name
     */
    public PluginChannel(String name) {
        this.name = name;
    }

    /**
     * Get plugin channel name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Handle the bytes
     *
     * @param is data buffer
     */
    public abstract void handleData(DataInputStream is) throws IOException;

    /**
     * Data to write
     *
     * @param dos
     */
    public abstract void registerToServer(DataOutputStream dos) throws IOException;

}
