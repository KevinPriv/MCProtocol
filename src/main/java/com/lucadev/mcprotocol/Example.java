package com.lucadev.mcprotocol;

import com.lucadev.mcprotocol.auth.LoginProvider;

import java.io.IOException;
import java.util.Scanner;

/**
 * Code I mess around in to test stuff.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Example {

    public static void main(String[] args) {
        //init builder w some info in params
        BotBuilder builder = new BotBuilder("localhost", 25565);
        //Set authenticate to offline mode
        builder.loginProvider(LoginProvider.getDefaultOfflineProvider()).username("Steve").authenticate();
        //build to bot
        Bot bot = builder.build();
        //additional bot config can be done here
        try {
            //connect
            bot.connect();
            bot.joinServer();
            //bot.consoleChat();
            Scanner scanner = new Scanner(System.in);
            while(bot.getConnection().getSocket().isConnected()) {
                String msg = scanner.nextLine();
                bot.sendChatMessage(msg);
            }
            System.out.println("BOT FINISHED BLOCKING!");
            bot.getConnection().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
