package example;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.bots.BotBuilder;
import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.bots.PlayerBot;

import java.io.IOException;
import java.util.Scanner;

/**
 * Example of how to join a server and start chatting.
 * SessionProvider needs to change to the online provider if you wish to join online mode servers.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ExampleSeverJoinAndChat {

    public static void main(String[] args) {
        //init builder w some info in params
        BotBuilder builder = new BotBuilder("localhost", 25565);
        //Set authenticate to offline mode and authenticate with username and password.
        //Since we use the offline provider it does not check username and password and returns an offline session.
        //This allows us to only join offline mode servers. To also join online mode use the default online authenticate provider.
        builder.sessionProvider(SessionProvider.getDefaultOfflineProvider()).username("Steve").password("").authenticate();

        PlayerBot bot = builder.buildPlayerBot();
        //additional bots config can be done here
        try {
            //connect
            bot.joinServer();

            Scanner scanner = new Scanner(System.in);
            while(bot.isConnected()) {
                String msg = scanner.nextLine();
                bot.sendChatMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
