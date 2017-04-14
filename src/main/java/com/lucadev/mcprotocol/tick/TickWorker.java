package com.lucadev.mcprotocol.tick;

import com.lucadev.mcprotocol.Bot;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class TickWorker {

    private int counter;
    private int delay;
    private TickListener listener;
    private Bot bot;

    public TickWorker(Bot bot, int delay, TickListener listener) {
        this.bot = bot;
        this.delay = delay;
        this.listener = listener;
    }

    public void tick() {
        counter++;
        if(counter == delay) {
            listener.onAction(bot);
            counter = 0;
        }
    }

}
