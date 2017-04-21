package com.lucadev.mcprotocol.protocol.network.client.util;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;

import java.io.IOException;

/**
 * Thread that is used to read from the input stream.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ReadTask extends Thread {

    private Bot bot;
    private NetClient netClient;

    public ReadTask(Bot bot) {
        if (bot == null) {
            throw new NullPointerException("Bot parameter may not be null.");
        }
        this.bot = bot;
        netClient = bot.getNetClient();
    }

    @Override
    public void run() {
        setName("READ");
        while (bot.isConnected()) {
            try {
                netClient.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
