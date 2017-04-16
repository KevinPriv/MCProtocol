package com.lucadev.mcprotocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.game.entity.player.Player;
import com.lucadev.mcprotocol.game.world.World;
import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.ProtocolFactory;
import com.lucadev.mcprotocol.protocol.State;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;
import com.lucadev.mcprotocol.protocol.packets.headers.PacketLengthHeader;
import com.lucadev.mcprotocol.protocol.packets.sbound.handshake.S00Handshake;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.S02ChatMessage;
import com.lucadev.mcprotocol.protocol.packets.sbound.status.S00Request;
import com.lucadev.mcprotocol.util.model.MOTDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;

/**
 * Connects all classes together and functions as the actual bot.
 * This class could be seen as the player who connects to the server.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Bot {

    /**
     * SLF4J logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    /* Handles the mc protocol */
    private final Protocol protocol;
    /* Handles IO between hosts */
    private NetClient netClient;
    private Connection connection;

    /* Handles authentication */
    private final SessionProvider sessionProvider;

    /* Session in use by the bot */
    private Session session;

    /* Player and world information are stored in here */
    private Player player;
    private World world;

    /* Builder that contains config for the bot */
    private final BotBuilder botBuilder;

    /**
     * Constructs a bot object from the given builder configuration.
     * @param builder bot configuration.
     */
    public Bot(BotBuilder builder) {
        this.botBuilder = builder;
        this.sessionProvider = builder.getSessionProvider();
        this.protocol = builder.getProtocolFactory().createProtocol(botBuilder.getProtocolVersion());

        if (builder.shouldAuthenticate()) {
            if(sessionProvider == null) {
                throw new IllegalStateException("Cannot authenticate without available SessionProvider(it is null)");
            }
            try {
                session = sessionProvider.authenticate(builder.getUsername(), builder.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protocol.setup(this);
        //We always want a secured connection even though we might not use it.
        connection = protocol.getConnectionFactory().createSecureConnection(botBuilder.getSocketFactory());
        netClient = protocol.getNetClientFactory().createClient(this);
    }

    /**
     * Setup all connection objects and opens the streams.
     * The configured bot address and port are used.
     * @throws IOException when something goes wrong while connecting.
     */
    public void connect() throws IOException {
        connect(botBuilder.getHost(), botBuilder.getPort());
    }

    /**
     * Disconnect from the server
     * @throws IOException when something goes wrong disconnecting.
     */
    private void disconnect() throws IOException {
        getProtocol().disconnect();
    }

    /**
     * Connects to the given host and port.
     * @param host hostname/ip address of the server
     * @param port port that the server is listening on.
     * @throws IOException when we cannot connect or something goes wrong.
     */
    private void connect(String host, int port) throws IOException {
        if (isConnected()) {
            throw new IllegalStateException("Already connected!");
        }
        connection.connect(host, port);
    }

    /**
     * Joins the server at the given hostname/ip address and port.
     * @throws IOException gets thrown when something goes wrong joining the server
     */
    public void joinServer() throws IOException {
        logger.info("Joining server as player...");
        protocol.serverLogin();
    }

    /**
     * Fetches MOTD
     * @return MOTD response containing the motd of the server
     * @throws IOException
     */
    public MOTDResponse fetchMOTD() throws IOException {
        return protocol.getMOTD();
    }

    public ProtocolFactory getProtocolFactory() {
        return botBuilder.getProtocolFactory();
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    public Connection getConnection() {
        return connection;
    }

    public NetClient getNetClient() {
        return netClient;
    }

    public Session getSession() {
        return session;
    }

    public SessionProvider getSessionProvider() {
        return sessionProvider;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void blockUntilFinished() {
        while (getConnection().isConnected()) {
        }
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * @return builder used to configure this bot.
     */
    public BotBuilder getBuilder() {
        return botBuilder;
    }
}
