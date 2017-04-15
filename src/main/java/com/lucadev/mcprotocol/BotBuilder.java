package com.lucadev.mcprotocol;

import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.protocol.ProtocolFactory;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;

/**
 * Builder for the bot.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class BotBuilder {

    private static final int DEFAULT_MC_PORT = 25565;

    private String host;
    private int port;

    private ConnectionFactory connectionFactory = ConnectionFactory.getDefault();
    private ProtocolFactory protocolFactory = ProtocolFactory.getDefault();
    private NetClientFactory netClientFactory = NetClientFactory.getDefault();

    /**
     * Used to obtain the mojang auth
     */
    private SessionProvider sessionProvider = SessionProvider.getDefaultOnlineProvider();

    private String username;
    private String password;
    //Should we authenticate?
    private boolean authenticate = true;

    /**
     * Protocol version
     */
    private int version = ProtocolFactory.LATEST_VERSION;

    public BotBuilder() {
        this("localhost");
    }

    public BotBuilder(String host) {
        this(host, DEFAULT_MC_PORT);
    }

    public BotBuilder(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public BotBuilder host(String host) {
        this.host = host;
        return this;
    }

    public BotBuilder port(int port) {
        this.port = port;
        return this;
    }

    public BotBuilder connectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        return this;
    }

    public BotBuilder protocolFactory(ProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
        return this;
    }

    public Bot build() {
        return new Bot(this);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public ProtocolFactory getProtocolFactory() {
        return protocolFactory;
    }

    public int getVersion() {
        return version;
    }

    /**
     * Protocol version!
     *
     * @param version
     */
    public BotBuilder version(int version) {
        this.version = version;
        return this;
    }

    public SessionProvider getSessionProvider() {
        return sessionProvider;
    }

    public BotBuilder loginProvider(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BotBuilder username(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BotBuilder password(String password) {
        this.password = password;
        return this;
    }

    public BotBuilder authenticate() {
        this.authenticate = true;
        return this;
    }

    public boolean shouldAuthenticate() {
        return authenticate;
    }

    public NetClientFactory getNetClientFactory() {
        return netClientFactory;
    }

    public BotBuilder netClientFactory(NetClientFactory netClientFactory) {
        this.netClientFactory = netClientFactory;
        return this;
    }
}
