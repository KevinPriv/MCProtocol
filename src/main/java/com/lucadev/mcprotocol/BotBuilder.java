package com.lucadev.mcprotocol;

import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.protocol.ProtocolFactory;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;

import javax.net.SocketFactory;

/**
 * Builder that is used to create a bot.
 * By using a separate builder class we can manage advanced bot configurations.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class BotBuilder {

    /**
     * Default networking port used by the Minecraft protocol.
     */
    private static final int DEFAULT_MC_PORT = 25565;

    private String host;
    private int port;

    private SocketFactory socketFactory = SocketFactory.getDefault();
    private ProtocolFactory protocolFactory = ProtocolFactory.getDefault();

    private SessionProvider sessionProvider = SessionProvider.getDefaultOnlineProvider();
    private String username;
    private String password;
    private boolean authenticate = false;

    private int protocolVersion = ProtocolFactory.LATEST_VERSION;

    /**
     * Constructor using all default values
     */
    public BotBuilder() {
        this("localhost");
    }

    /**
     * Constructor specifying the server's ip address or hostname
     * @param host ip address or hostname of the Minecraft server we want to connect to.
     */
    public BotBuilder(String host) {
        this(host, DEFAULT_MC_PORT);
    }

    /**
     * Constructor specifying the server's ip address or hostname and port number
     * @param host ip address or hostname of the Minecraft server we want to connect to.
     * @param port the port that the Minecraft server runs on.
     */
    public BotBuilder(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @param host the ip address or hostname of the server we want to connect to.
     * @return instance of this builder.
     */
    public BotBuilder host(String host) {
        this.host = host;
        return this;
    }

    /**
     * @param port port number that the Minecraft server is operating on.
     * @return instance of this builder.
     */
    public BotBuilder port(int port) {
        this.port = port;
        return this;
    }

    /**
     * @param protocolFactory factory used to create the protocol implementation of the specified version.
     * @return instance of this builder.
     */
    public BotBuilder protocolFactory(ProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
        return this;
    }

    /**
     * @return a new Bot instance created from this builder configuration.
     */
    public Bot build() {
        return new Bot(this);
    }

    /**
     * @return hostname or ip address of the Minecraft server to connect to.
     */
    public String getHost() {
        return host;
    }

    /**
     * @return port number on which the Minecraft server is operating.
     */
    public int getPort() {
        return port;
    }

    /**
     * @return factory used to create the protocol implementation from the given protocol version.
     */
    public ProtocolFactory getProtocolFactory() {
        return protocolFactory;
    }

    /**
     * @return integer value of the protocol id to use.
     */
    public int getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * @param version the protocol version to use
     * @return instance of this builder.
     */
    public BotBuilder protocolVersion(int version) {
        this.protocolVersion = version;
        return this;
    }

    /**
     * @return provider that authenticates users/actions.
     */
    public SessionProvider getSessionProvider() {
        return sessionProvider;
    }

    /**
     * @param sessionProvider provider used to authenticate users/actions.
     * @return instance of this builder.
     */
    public BotBuilder sessionProvider(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
        return this;
    }

    /**
     * @return player username that will be used by the bot.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username player username that will be used.
     * @return instance of this builder.
     */
    public BotBuilder username(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return password for the given username.
     * @see #getUsername()
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password password to use for the given username.
     * @return instance of this builder.
     * @see #getUsername()
     */
    public BotBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Enables authentication so we will actually obtain a Session object.
     * @return instance of this builder.
     * @see com.lucadev.mcprotocol.auth.Session
     */
    public BotBuilder authenticate() {
        this.authenticate = true;
        return this;
    }

    /**
     * @return decision on whether we should authenticate through our SessionProvider or skip authentication.
     */
    public boolean shouldAuthenticate() {
        return authenticate;
    }

    /**
     * @return Factory used to create socket instances
     */
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * @param socketFactory socket factory to use when creating a socket.
     * @return instance of this builder.
     */
    public BotBuilder socketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }
}
