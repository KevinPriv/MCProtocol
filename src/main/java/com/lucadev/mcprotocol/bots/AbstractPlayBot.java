package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.game.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Extended abstract implementation of a Bot with minimal requirements to actually join a multiplayer game.
 * This abstract implementation also features sessions and a method to start the game join process.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractPlayBot extends AbstractBot implements PlayBot {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractPlayBot.class);

    private SessionProvider sessionProvider;
    private Session session;
    private Player player;

    /**
     * Construct a new bots using the given config.
     * Requests a secure connection when setting up the connection object.
     *
     * @param botBuilder the builder contains all required config to setup the bots.
     */
    public AbstractPlayBot(BotBuilder botBuilder) {
        super(botBuilder);
        sessionProvider = botBuilder.getSessionProvider();
        //check if we should auth
        if(botBuilder.shouldAuthenticate()) {
            //yup
            try {
                authenticate(botBuilder.getUsername(), botBuilder.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Code to run to join the actual server and game.
     * Returns when the protocol state reaches PLAY
     * @throws IOException thrown when we cannot join the server somehow.
     */
    @Override
    public void joinServer() throws IOException {
        if(!isConnected()) {
            connect();
        }
        getProtocol().serverLogin();
    }

    /**
     * Setup all connection objects and opens the streams.
     * The configured bots address and port from the builder are used.
     *
     * @throws IOException when something goes wrong while connecting.
     */
    @Override
    public void connect() throws IOException {
        connect(getBotBuilder().getHost(), getBotBuilder().getPort());
    }

    /**
     * Same as the connect method but here you may specify the hostname and port yourself.
     *
     * @param host hostname/ip address
     * @param port port number that the server is listening on.
     * @throws IOException when we could not open the connection.
     */
    @Override
    public void connect(String host, int port) throws IOException {
        if(isConnected()) {
            throw new IllegalStateException("May not connect when already connected.");
        }
        getConnection().connect(host, port);
    }

    /**
     * Disconnect the current connection and streams.
     * Implementations may also put protocol calls in this.
     *
     * @throws IOException when something goes wrong disconnecting.
     */
    @Override
    public void disconnect() throws IOException {
        if(!isConnected()) {
            throw new IllegalStateException("Cannot disconnect when not connected.");
        }
        //Let the protocol handle any disconnect calls since it might be protocol specific.
        getProtocol().disconnect();
    }

    /**
     * @return session that the implementor is currently authenticated with.
     */
    @Override
    public Session getSession() {
        return session;
    }

    /**
     * @return provider implementation used to authenticate with.
     */
    @Override
    public SessionProvider getSessionProvider() {
        return sessionProvider;
    }

    /**
     * Authenticate and replace the current session.
     *
     * @param email    email or username
     * @param password password to auth with
     * @throws IOException if authentication fails somehow.
     */
    @Override
    public void authenticate(String email, String password) throws IOException {
        setSession(getSessionProvider().authenticate(email, password));
    }

    /**
     * Sets the session provider we should use when authenticating.
     * @param sessionProvider implementation of SessionProvider to use when authenticating next time.
     */
    protected void setSessionProvider(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    /**
     * Sets the session currently in use by the bots.
     * May only be changed when the bots is not connected.
     * @param session the session for the bots to use.
     */
    protected void setSession(Session session) {
        if(isConnected()) {
            throw new IllegalArgumentException("May only change session when not connected. This is to prevent possible crashes/instabilities.");
        }
        this.session = session;
    }

    /**
     * Since there are some essential player packets we will require atleast some form of player object to handle those.
     * @return Model of the bot's minecraft player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the minecraft player model.
     * @param player player model for this bot.
     */
    public void setPlayer(Player player) {
        if(this.player != null) {
            throw new IllegalStateException("May not set player object twice!");
        }
        this.player = player;
    }
}
