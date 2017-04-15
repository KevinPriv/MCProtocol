package com.lucadev.mcprotocol.game.tick;

import com.lucadev.mcprotocol.Bot;

/**
 * TickWorkers wrap around tick listeners and provide the rest of the required data such as how many ticks to wait,
 * a tick counter and more. The internal counter will reset once the delay goal is achieved.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class TickWorker {

    private int counter = 0;
    private int delay;
    private TickListener listener;
    private Bot bot;

    /**
     * Constructor setting up the worker with the given data.
     * @param bot instance to our bot
     * @param delay how many ticks to wait until we can execute the listener
     * @param listener the listener we will invoke once X amount of ticks have elapsed.
     */
    public TickWorker(Bot bot, int delay, TickListener listener) {
        this.bot = bot;
        this.delay = delay;
        this.listener = listener;
    }

    /**
     * Called by the tick engine, handles internal worker counter and the listener.
     */
    public void tick() {
        if(delay <= 1) {
            listener.onAction(bot);
            return;
        }
        counter++;
        if (counter == delay) {
            listener.onAction(bot);
            counter = 0;
        }
    }

}
