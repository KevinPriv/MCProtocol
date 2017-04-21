package com.lucadev.mcprotocol.protocol.pluginchannel;

import com.lucadev.mcprotocol.protocol.pluginchannel.impl.DefaultPluginChannelManagerFactory;

/**
 * TODO: make factory an interface and return default factory
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class PluginChannelManagerFactory {

    private static PluginChannelManagerFactory defaultFactory;

    /**
     * @return a new plugin channel manager.
     */
    public abstract PluginChannelManager createPluginChannelManager();

    /**
     * @return singleton instance of the default factory implementation.
     */
    public static PluginChannelManagerFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = new DefaultPluginChannelManagerFactory();
        }

        return defaultFactory;
    }

}
