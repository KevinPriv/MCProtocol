package com.lucadev.mcprotocol.game.tick;

import com.lucadev.mcprotocol.Bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract tick engine that contains some pre written methods.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractTickEngine implements TickEngine {

    private Bot bot;
    private List<TickWorker> workers;

    /**
     * Constructor that requires an instance of our bot
     * @param bot the instance of our bot
     */
    public AbstractTickEngine(Bot bot) {
        this.bot = bot;
        workers = new ArrayList<>();
    }

    /**
     * @return list of tick workers registered to this engine.
     */
    @Override
    public List<TickWorker> getTickWorkers() {
        return workers;
    }

    /**
     * Register for the tick engine.
     *
     * @param delay    every X ticks the listener will be executed.
     * @param listener the listener
     */
    @Override
    public void register(int delay, TickListener listener) {
        workers.add(new TickWorker(bot, delay, listener));
    }

    /**
     * @return instance of our bot
     */
    protected Bot getBot() {
        return bot;
    }
}
