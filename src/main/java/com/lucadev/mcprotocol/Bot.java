package com.lucadev.mcprotocol;

import com.google.gson.Gson;
import com.lucadev.mcprotocol.game.entity.player.Player;
import com.lucadev.mcprotocol.auth.LoginProvider;
import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.game.world.World;
import com.lucadev.mcprotocol.protocol.network.connection.Connection;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;
import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.ProtocolFactory;
import com.lucadev.mcprotocol.protocol.State;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.packet.headers.PacketLengthHeader;
import com.lucadev.mcprotocol.protocol.packet.sbound.handshake.S00Handshake;
import com.lucadev.mcprotocol.protocol.packet.sbound.status.S00Request;
import com.lucadev.mcprotocol.protocol.packet.sbound.play.S02ChatMessage;
import com.lucadev.mcprotocol.util.model.MOTDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Connects all classes together and functions as the actual bot.
 * This class could be seen as the player who connects to the server.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Bot {

    /**
     * SLF4J logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    /* Handles raw data streams */
    private ConnectionFactory connectionFactory;

    /* Handles protocol specification */
    private ProtocolFactory protocolFactory;
    private Protocol protocol;

    /* Handles communication between client and server */
    private NetClientFactory netClientFactory;
    private NetClient netClient;

    /**
     * Builder that was used to setup this class.
     */
    private BotBuilder botBuilder;

    /**
     * Login provider.
     */
    private LoginProvider loginProvider;

    /**
     * Auth information
     */
    private Session login;

    /**
     * State of the connection
     */
    private boolean connected = false;

    /**
     * The player himself.
     */
    private Player player;

    private World world;

    /**
     * Constructs a bot from information through the botBuilder class.
     */
    public Bot(BotBuilder builder) {
        this.connectionFactory = builder.getConnectionFactory();
        this.protocolFactory = builder.getProtocolFactory();
        this.netClientFactory = builder.getNetClientFactory();
        this.loginProvider = builder.getLoginProvider();
        if(builder.shouldAuthenticate()) {
            try {
                authenticate(builder.getUsername(), builder.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.botBuilder = builder;
    }

    public void authenticate(String username, String password) throws IOException{
        logger.info("Authenticating user");
        login = loginProvider.login(username, password);
        logger.info(login.toString());
    }

    public void connect() throws IOException {
        connect(botBuilder.getHost(), botBuilder.getPort());
    }

    public void disconnect() {
        getProtocol().disconnect();
    }

    /**
     * Connects to the server.
     * @param host
     * @param port
     */
    public void connect(String host, int port) throws IOException {
        if(isConnected()) {
            throw new IllegalStateException("Already connected!");
        }
        Connection connection = connectionFactory.createConnection();
        protocol = protocolFactory.createProtocol(botBuilder.getVersion());
        protocol.setup(this);
        connection.connect(host, port);
        netClient = netClientFactory.createClient(this, connection);
        connected = true;
    }

    /**
     * Joins the server by following the serverLogin and whatnot.
     * @throws IOException
     */
    public void joinServer() throws IOException {
        if(!isConnected()) {
            connect();
        }
        logger.info("Joining server as player...");
        protocol.serverLogin();
    }

    /**
     * Fetches motd
     * @return
     * @throws IOException
     */
    public MOTDResponse fetchMOTD() throws IOException {
        if(!isConnected()) {
            connect();
        }
        logger.info("Fetching MOTD");
        protocol.setCurrentState(State.STATUS);
        netClient.writePacket(new S00Handshake(botBuilder.getHost(), botBuilder.getPort(),
                getProtocol().getVersion(), State.STATUS));
        netClient.writePacket(new S00Request());

        PacketLengthHeader respHead = netClient.readHeader();//since we're only expecting a confirmation we can skip using an actual packet

        byte[] response = netClient.readResponse();
        logger.info("Response from server: {}", new String(response));
        return new Gson().fromJson(new String(response), MOTDResponse.class);
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ProtocolFactory getProtocolFactory() {
        return protocolFactory;
    }

    public void setProtocolFactory(ProtocolFactory protocolFactory) {
        this.protocolFactory = protocolFactory;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public boolean isConnected() {
        return connected;
    }

    public Connection getConnection() {
        return netClient.getConnection();
    }

    public NetClient getNetClient() {
        return netClient;
    }

    public Session getSession() {
        return login;
    }

    public LoginProvider getLoginProvider() {
        return loginProvider;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void blockUntilFinished() {
        while(getConnection().getSocket().isConnected()) {
        }
    }

    public void sendChatMessage(String message) {
        getNetClient().sendPacket(new S02ChatMessage(message));
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
