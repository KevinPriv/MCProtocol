package example;

import com.lucadev.mcprotocol.bots.BotBuilder;
import com.lucadev.mcprotocol.bots.MOTDBot;
import com.lucadev.mcprotocol.util.model.MOTDResponse;

import java.io.IOException;

/**
 * Example code on fetching a server MOTD
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ExampleMOTD {

    public static void main(String[] args) {
        //init builder w some info in params
        BotBuilder builder = new BotBuilder("localhost", 25565);
        //buildPlayerBot to bots
        MOTDBot bot = builder.buildMOTDBot();
        //additional bots config can be done here
        try {
            MOTDResponse response = bot.getMOTD();
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
