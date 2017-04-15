package com.lucadev.mcprotocol.game.tick;

import java.util.List;

/**
 * Interface for tick engines.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface TickEngine extends Runnable {

    /**
     * Start the tickEngine
     * @param thread should we run in a separate thread or the current thread?
     */
    void start(boolean thread);

    /**
     * Stops the tick engine once the current tick has finished.
     */
    void stop();

    /**
     * @return list of tick workers registered to this engine.
     */
    List<TickWorker> getTickWorkers();

    /**
     * Register for the tick engine.
     * @param delay every X ticks the listener will be executed.
     * @param listener the listener
     */
    void register(int delay, TickListener listener);

}
