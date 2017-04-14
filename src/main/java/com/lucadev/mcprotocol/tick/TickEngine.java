package com.lucadev.mcprotocol.tick;

import com.lucadev.mcprotocol.Bot;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class TickEngine extends Thread {

    public static final long TPS = 20;
    public static final long DELAY = 1000/TPS;

    private boolean running = false;
    private Bot bot;

    public TickEngine(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        setName("TICK-ENGINE");
        running = true;
        while(bot.getConnection().getSocket().isConnected() && running) {
            bot.getProtocol().tick();
            try {
                sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTicking() {
        running = false;
    }
}
