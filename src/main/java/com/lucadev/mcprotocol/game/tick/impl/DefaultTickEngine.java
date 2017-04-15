package com.lucadev.mcprotocol.game.tick.impl;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.tick.AbstractTickEngine;

/**
 * Default implementation of a tick engine.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultTickEngine extends AbstractTickEngine {

    /**
     * TickPerSecond
     */
    public static final long TPS = 20;

    /**
     * Sleep delay to fit tps into a second with equal delays.
     */
    public static final long DELAY = 1000 / TPS;

    private boolean running;

    /**
     * Constructor required to call super constructor.
     * @param bot an instance of our bot.
     */
    public DefaultTickEngine(Bot bot) {
        super(bot);
    }

    /**
     * Start the tickEngine
     *
     * @param thread should we run in a separate thread or the current thread?
     */
    @Override
    public void start(boolean thread) {
        if(thread) {
            Thread t = new Thread(this);
            t.setName(toString());
            t.start();
        } else {
            run();
        }
    }

    /**
     * Stops the tick engine once the current tick has finished.
     */
    @Override
    public void stop() {
        running = false;
    }

    /**
     * May be threaded depending on the boolean given in the start method. If not threaded it will run in the same thread.
     * @see Runnable#run()
     */
    @Override
    public void run() {
        running = true;
        while (running && getBot().isConnected()) {
            getTickWorkers().forEach(worker -> worker.tick());
        }
    }
}
