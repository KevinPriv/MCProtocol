package com.lucadev.mcprotocol.protocol.impl.v316;

import com.lucadev.mcprotocol.bots.AbstractPlayBot;
import com.lucadev.mcprotocol.bots.PlayBot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.game.Dimension;
import com.lucadev.mcprotocol.game.GameMode;
import com.lucadev.mcprotocol.game.entity.player.BotPlayer;
import com.lucadev.mcprotocol.game.entity.player.Player;
import com.lucadev.mcprotocol.protocol.Protocol;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.C13ServerDifficulty;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player.C43PlayerAbilities;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player.C46PlayerPositionLook;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.entity.player.C67SpawnPosition;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.entity.player.S00TeleportConfirm;
import com.lucadev.mcprotocol.protocol.packets.sbound.play.entity.player.S13PlayerPositionLook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Concrete implementation of Player which works with protocol 316
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see Player
 */
public class Player316 extends BotPlayer {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Player316.class);

    public Player316(int entityId, String uuid, String username, GameMode gameMode, Dimension dimension, Difficulty difficulty, String levelType, boolean showReducedDebugInfo) {
        super(entityId, uuid, username, gameMode, dimension, difficulty, levelType, showReducedDebugInfo);
    }

    /**
     * Register listeners for the packets required to manage the player object.
     * May also register tick workers in here
     * @param playBot minimum bot implementation to handle a player
     */
    @Override
    public void registerProtocolListeners(PlayBot playBot) {
        logger.info("Registering protocol specific player listeners.");
        Protocol prot = playBot.getProtocol();

        prot.registerPacketListener(C13ServerDifficulty.class, (bot, packet) -> {
            C13ServerDifficulty serverDifficulty = (C13ServerDifficulty) packet;
            setDifficulty(serverDifficulty.getDifficulty());
        });

        prot.registerPacketListener(C67SpawnPosition.class, (bot, packet) -> {
            C67SpawnPosition spawnPosition = (C67SpawnPosition) packet;
            //logger.info("Updated spawn positon to " + spawnPosition.getPosition().toString());
        });

        prot.registerPacketListener(C43PlayerAbilities.class, (bot, packet) -> {
            C43PlayerAbilities playerAbilities = (C43PlayerAbilities) packet;
            //logger.info("Updated player abilities: {}", playerAbilities.getPlayerAbilities().toString());
            setPlayerAbilities(playerAbilities.getPlayerAbilities());
        });

        prot.registerPacketListener(C46PlayerPositionLook.class, (bot, packet) -> {
            C46PlayerPositionLook positionLook = (C46PlayerPositionLook) packet;
            setPosition(positionLook.getX(), positionLook.getY(), positionLook.getZ());
            setYawPitch(positionLook.getYaw(), positionLook.getPitch());
            //send tp confirmation
            //logger.info("Updating player pos from server");
            try {
                bot.getNetClient().sendPacket(new S00TeleportConfirm(positionLook.getTeleportId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Send player pos and look every 1 sec when not moving.
        prot.getTickEngine().register(20, (bot) -> {
            try {
                bot.getNetClient().sendPacket(new S13PlayerPositionLook(this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
