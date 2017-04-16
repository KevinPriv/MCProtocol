package com.lucadev.mcprotocol.game.tick;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.tick.impl.DefaultTickEngineFactory;

/**
 * Factory that creates tick engines which are used to manage minecraft game ticks(unit of time).
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class TickEngineFactory {

    private static TickEngineFactory defaultFactory;

    /**
     * Create a tick engine
     * @param bot a reference to the Bot which will be used by the tick engine.
     * @return
     */
    public abstract TickEngine createEngine(Bot bot);

    /**
     * @return obtain a singleton instance of the default factory for tick engines.
     */
    public static TickEngineFactory getDefaultFactory() {
        if(defaultFactory == null) {
            defaultFactory = new DefaultTickEngineFactory();
        }
        return defaultFactory;
    }

}
