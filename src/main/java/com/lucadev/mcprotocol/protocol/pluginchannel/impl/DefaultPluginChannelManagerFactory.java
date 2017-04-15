package com.lucadev.mcprotocol.protocol.pluginchannel.impl;

import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManager;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManagerFactory;

/**
 * Default factory implementation for plugin channel manager
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultPluginChannelManagerFactory extends PluginChannelManagerFactory {

    /**
     * @return a new plugin channel manager.
     */
    @Override
    public PluginChannelManager createPluginChannelManager() {
        return new DefaultPluginChannelManager();
    }
}
