package com.lucadev.mcprotocol.game.tick;

import com.lucadev.mcprotocol.bots.Bot;

/**
 * Listener interface for ticks.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface TickListener {

    /**
     * When the configured amount of ticks have collapsed the onAction method will be called by the tick engine.
     *
     * @param bot the instance of our bots
     */
    void onAction(Bot bot);
}
