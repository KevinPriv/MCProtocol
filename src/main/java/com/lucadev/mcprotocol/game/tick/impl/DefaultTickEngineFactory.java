package com.lucadev.mcprotocol.game.tick.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.tick.TickEngine;
import com.lucadev.mcprotocol.game.tick.TickEngineFactory;

/**
 * Default implementation of TickEngineFactory
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultTickEngineFactory extends TickEngineFactory {

    /**
     * Create a tick engine
     *
     * @param bot a reference to the Bot which will be used by the tick engine.
     * @return
     */
    @Override
    public TickEngine createEngine(Bot bot) {
        return new DefaultTickEngine(bot);
    }
}
