package com.lucadev.mcprotocol.protocol.network.client.util;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;

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
        setName("READ");
        while(bot.getConnection().getSocket().isConnected()) {
            netClient.tickRead();
        }
    }
}
