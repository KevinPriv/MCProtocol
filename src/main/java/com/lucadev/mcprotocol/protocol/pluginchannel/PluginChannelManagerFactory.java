package com.lucadev.mcprotocol.protocol.pluginchannel;

import com.lucadev.mcprotocol.protocol.pluginchannel.impl.SimplePluginChannelManager;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class PluginChannelManagerFactory {

    public static PluginChannelManager createDefault() {
        return new SimplePluginChannelManager();
    }

}
