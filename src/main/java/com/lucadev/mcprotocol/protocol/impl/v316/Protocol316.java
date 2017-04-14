package com.lucadev.mcprotocol.protocol.impl.v316;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.game.entity.BotPlayer;
import com.lucadev.mcprotocol.game.world.World;
import com.lucadev.mcprotocol.protocol.AbstractProtocol;

import com.lucadev.mcprotocol.protocol.State;
import com.lucadev.mcprotocol.protocol.client.NetClient;
import com.lucadev.mcprotocol.protocol.client.ReadTask;
import com.lucadev.mcprotocol.protocol.packets.PacketListener;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;
import com.lucadev.mcprotocol.protocol.packets.cbound.login.C00Disconnect;
import com.lucadev.mcprotocol.protocol.packets.cbound.login.C01EncryptionRequest;
import com.lucadev.mcprotocol.protocol.packets.cbound.login.C02LoginSuccess;
import com.lucadev.mcprotocol.protocol.packets.cbound.login.C03SetCompression;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.*;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.*;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.map.C09UpdateBlockEntity;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.map.C32ChunkData;
import com.lucadev.mcprotocol.protocol.packets.sbound.handshake.S00Handshake;
import com.lucadev.mcprotocol.protocol.packets.sbound.handshake.S00LoginStart;
import com.lucadev.mcprotocol.protocol.packets.sbound.handshake.S01EncryptionResponse;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.*;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManager;
import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannelManagerFactory;
import com.lucadev.mcprotocol.protocol.pluginchannel.channels.MCBrandPluginChannel;
import com.lucadev.mcprotocol.tick.TickEngine;
import com.lucadev.mcprotocol.tick.TickListener;
import com.lucadev.mcprotocol.tick.TickWorker;
import com.lucadev.mcprotocol.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Protocol316 extends AbstractProtocol {

    private Bot bot;
    private static final Logger logger = LoggerFactory.getLogger(Protocol316.class);
    private PluginChannelManager pluginChannelManager;
    private HashMap<Class<? extends ReadablePacket>, List<PacketListener>> listenerMap = new HashMap<>();
    private List<TickWorker> tickWorkers = new ArrayList<>();
    private TickEngine tickEngine;

    /**
     * Setup everything in here
     *
     * @param bot
     */
    @Override
    public void setup(Bot bot) {
        this.bot = bot;
        tickEngine = new TickEngine(bot);
        pluginChannelManager = PluginChannelManagerFactory.createDefault();
        setupPluginChannels();
        setupPacketListeners();
        setupTickWorkers();
        register(State.LOGIN, 0x00, C00Disconnect.class);
        register(State.LOGIN, 0x01, C01EncryptionRequest.class);
        register(State.LOGIN, 0x02, C02LoginSuccess.class);
        register(State.LOGIN, 0x03, C03SetCompression.class);
        register(State.PLAY, 0x23, C35JoinGame.class);
        register(State.PLAY, 0x0D, C13ServerDifficulty.class);
        register(State.PLAY, 0x18, C24PluginMessage.class);
        register(State.PLAY, 0x43, C67SpawnPosition.class);
        register(State.PLAY, 0x2B, C43PlayerAbilities.class);
        register(State.PLAY, 0x2E, C46PlayerPositionLook.class);
        register(State.PLAY, 0x37, C55SlotChange.class);//WRITE HANDLER
        register(State.PLAY, 0x0F, C16ChatMessage.class);
        register(State.PLAY, 0x1F, C31KeepAlive.class);
        register(State.PLAY, 0x1A, C26Disconnect.class);
        register(State.PLAY, 0x2D, C45UpdatePlayerListItem.class);
        register(State.PLAY, 0x20, C32ChunkData.class);
        register(State.PLAY, 0x03, C03SpawnMob.class);
        register(State.PLAY, 0x39, C57UpdateEntityMetadata.class);
        register(State.PLAY, 0x4A, C74EntitityProperties.class);
        register(State.PLAY, 0x34, C52EntityHeadLook.class);
        register(State.PLAY, 0x3C, C60EntityEquipment.class);
        register(State.PLAY, 0x25, C37EntityRelativeMove.class);
        register(State.PLAY, 0x09, C09UpdateBlockEntity.class);
    }

    private void setupTickWorkers() {
        //do this action every so often
        register(20, (bot) -> {
            bot.getNetClient().sendPacket(new S13PlayerPositionLook(bot.getPlayer()));
        });

    }

    /**
     * Setup all plugin channels required
     */
    private void setupPluginChannels() {
        pluginChannelManager.register(new MCBrandPluginChannel());
    }

    /**
     * Setup all packet listeners
     */
    private void setupPacketListeners() {
        registerPacketListener(C24PluginMessage.class, (bot, packet) -> {
            C24PluginMessage pluginMessage = (C24PluginMessage)packet;
            try {
                pluginChannelManager.handle(pluginMessage.getChannelName(), pluginMessage.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        registerPacketListener(C13ServerDifficulty.class, (bot, packet) -> {
            C13ServerDifficulty serverDifficulty = (C13ServerDifficulty)packet;
            bot.getPlayer().setDifficulty(serverDifficulty.getDifficulty());
        });

        registerPacketListener(C67SpawnPosition.class, (bot, packet) -> {
            C67SpawnPosition spawnPosition = (C67SpawnPosition)packet;
            //logger.info("Updated spawn positon to " + spawnPosition.getPosition().toString());
        });

        registerPacketListener(C43PlayerAbilities.class, (bot, packet) -> {
            C43PlayerAbilities playerAbilities = (C43PlayerAbilities)packet;
            //logger.info("Updated player abilities: {}", playerAbilities.getPlayerAbilities().toString());
            bot.getPlayer().setPlayerAbilities(playerAbilities.getPlayerAbilities());
        });

        registerPacketListener(C46PlayerPositionLook.class, (bot, packet) -> {
            C46PlayerPositionLook positionLook = (C46PlayerPositionLook)packet;
            bot.getPlayer().setPosition(positionLook.getX(), positionLook.getY(), positionLook.getZ());
            bot.getPlayer().setYawPitch(positionLook.getYaw(), positionLook.getPitch());
            //send tp confirmation
            //logger.info("Updating player pos from server");
            bot.getNetClient().sendPacket(new S00TeleportConfirm(positionLook.getTeleportId()));
        });

        registerPacketListener(C16ChatMessage.class, (bot, packet) -> {
            C16ChatMessage c16ChatMessage = (C16ChatMessage)packet;
            onChatMessage(c16ChatMessage.getChatComponent(), c16ChatMessage.getPosition());
        });

        registerPacketListener(C31KeepAlive.class, (bot, packet) -> {
            C31KeepAlive keepAlive = (C31KeepAlive)packet;
            //logger.info("KeepAlive received");
            bot.getNetClient().sendPacket(new S10KeepAlive(keepAlive.getKeepAliveId()));
        });

        registerPacketListener(C26Disconnect.class, (bot, packet) -> {
            logger.info("SERVER DISCONNECTED REASON: " + ((C26Disconnect)packet).getReason().getCompleteText());
            try {
                bot.getConnection().close();
                bot.getNetClient().shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getVersion() {
        return 316;
    }

    /**
     * Handshake step to join the server.
     */
    @Override
    public void serverLogin() throws IOException {
        String host = bot.getConnection().getSocket().getInetAddress().getCanonicalHostName();
        int port = bot.getConnection().getSocket().getPort();
        String username = bot.getLogin().getProfileName();
        NetClient client = bot.getNetClient();
        client.writePacket(new S00Handshake(host, port, getVersion(), State.LOGIN));
        client.writePacket(new S00LoginStart(username));
        if(bot.getLogin().isOnline()) {
            setupCrypto();
        }
        ReadablePacket compOrLoginPacket = client.readPacket();
        if(compOrLoginPacket instanceof C03SetCompression) {
            C03SetCompression comp = (C03SetCompression)compOrLoginPacket;
            enableCompression(comp.getThreshold());
            compOrLoginPacket = client.readPacket();
        }

        if(compOrLoginPacket instanceof C02LoginSuccess) {
            logger.info("Login success!!!");
            logger.info(compOrLoginPacket.toString());
        }

        setCurrentState(State.PLAY);
        ReadablePacket joingamePacket = client.readPacket();
        logger.info(joingamePacket.toString());
        if(joingamePacket instanceof C35JoinGame) {
            C35JoinGame joinGame = (C35JoinGame)joingamePacket;
            C02LoginSuccess loginSuccess = (C02LoginSuccess)compOrLoginPacket;
            bot.setPlayer(new BotPlayer(joinGame.getEntityId(), loginSuccess.getUuid(), loginSuccess.getUsername(), joinGame.getGameMode(), joinGame.getDimension(),
                    joinGame.getDifficulty(), joinGame.getLevelType(), joinGame.isReducedDebug()));
        }

        ReadablePacket packet = client.readPacket();
        while(!(packet instanceof C43PlayerAbilities)) {
            handlePacket(packet);
            packet = client.readPacket();
        }
        handlePacket(packet);
        pluginChannelManager.registerToServer(bot, client);

        client.writePacket(new S04ClientSettings());
        packet = client.readPacket();
        handlePacket(packet);
        client.writePacket(new S03ClientStatus(S03ClientStatus.ClientAction.PERFORM_RESPAWN));
        //start the tickengine
        bot.setWorld(new World());
        new ReadTask(bot).start();
        tickEngine.start();
    }

    @Override
    public void handlePacket(ReadablePacket packet) {
        if(listenerMap.containsKey(packet.getClass())) {
            listenerMap.get(packet.getClass()).forEach(listener -> listener.onPacket(bot, packet));
        }
    }

    @Override
    public void registerPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener) {
        if(!listenerMap.containsKey(clazz)) {
            listenerMap.put(clazz, new ArrayList<>());
        }

        listenerMap.get(clazz).add(listener);
    }

    @Override
    public void unregisterPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener) {
        if(!listenerMap.containsKey(clazz)) {
            return;
        }

        listenerMap.get(clazz).remove(listener);
        if(listenerMap.get(clazz).isEmpty()) {
            listenerMap.remove(clazz);
        }
    }

    @Override
    public void tick() {
        tickWorkers.forEach(TickWorker::tick);
    }

    /**
     * Chat message
     * @param component the chat component
     * @param position 0=chatbox, 1=system message, 2=above hotbar
     */
    @Override
    public void onChatMessage(ChatComponent component, byte position) {
        if(position == 1) {
            logger.info("CHAT: {}", component.getCompleteText());
        }
    }

    @Override
    public void register(int tickDelay, TickListener listener) {
        tickWorkers.add(new TickWorker(bot, tickDelay, listener));
    }

    @Override
    public void disconnect() {

    }

    @Override
    public TickEngine getTickEngine() {
        return tickEngine;
    }

    private void enableCompression(int threshold) {
        if(threshold < 0) {
            //skip since we need a positive integer
            return;
        }
        bot.getNetClient().enableCompression(threshold);
    }

    private void setupCrypto() throws IOException {
        NetClient client = bot.getNetClient();
        ReadablePacket readablePacket = client.readPacket();
        if(!(readablePacket instanceof C01EncryptionRequest)) {
            throw new IllegalStateException("Expected C01EncryptionRequest but got other packet instead!");
        }
        logger.info("Enabling crypto");
        C01EncryptionRequest cryptoRequest = (C01EncryptionRequest)readablePacket;
        try {
            PublicKey pubKey = EncryptionUtil.generatePublicKey(cryptoRequest.getPubKey());
            SecretKey secKey = EncryptionUtil.generateSecretKey();
            String hash = new BigInteger(EncryptionUtil.encrypt(cryptoRequest.getServerId(), pubKey, secKey)).toString(16);
            bot.getLoginProvider().authenticateServer(bot.getLogin(), hash);
            client.writePacket(new S01EncryptionResponse(pubKey, secKey, cryptoRequest.getVerifyToken()));
            client.setSharedKey(secKey);
            client.enableEncryption();
            client.enableDecryption();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}
