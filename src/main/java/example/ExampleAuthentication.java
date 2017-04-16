package example;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.BotBuilder;
import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.auth.yggdrasil.CacheEnabledYggdrasilSessionProvider;

/**
 * Example code on how sessions and their providers work.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ExampleAuthentication {

    public static void main(String[] args) {
        BotBuilder builder = new BotBuilder();
        //STANDARD OFFLINE MODE AUTHENTICATOR
        SessionProvider offlineProvider = SessionProvider.getDefaultOfflineProvider();
        //STANDARD ONLINE YGGDRASIL AUTHENTICATOR
        SessionProvider onlineProvider = SessionProvider.getDefaultOnlineProvider();
        //EXTENSION OF ONLINE PROVIDER THAT SAVES YOUR SESSION TO REUSE IT
        SessionProvider cacheEnabledProvider = new CacheEnabledYggdrasilSessionProvider();

        builder.sessionProvider(cacheEnabledProvider).username("EMAIL/USERNAME").password("PASSWORD").authenticate();
        //build to bot
        Bot bot = builder.build();//build will automatically run the auth steps

        //session information
        Session session = bot.getSession();
    }

}
