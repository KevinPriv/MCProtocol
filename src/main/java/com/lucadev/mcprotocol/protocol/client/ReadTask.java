package com.lucadev.mcprotocol.protocol.client;

import com.lucadev.mcprotocol.Bot;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ReadTask extends Thread {

    private Bot bot;
    private NetClient netClient;

    public ReadTask(Bot bot) {
        this.bot = bot;
        netClient = bot.getNetClient();
    }

    @Override
    public void run() {
        setName("INPUT-READER");
        while(bot.getConnection().getSocket().isConnected()) {
            netClient.tickRead();
        }
    }
}
