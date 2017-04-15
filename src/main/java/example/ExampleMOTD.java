package example;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.BotBuilder;
import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.util.model.MOTDResponse;

import java.io.IOException;
import java.util.Scanner;

/**
 * Example code on fetching a server MOTD
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ExampleMOTD {

    public static void main(String[] args) {
        //init builder w some info in params
        BotBuilder builder = new BotBuilder("localhost", 25565);
        //build to bot
        Bot bot = builder.build();
        //additional bot config can be done here
        try {
            MOTDResponse response = bot.fetchMOTD();
            System.out.println(response.toString());
            bot.getConnection().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
