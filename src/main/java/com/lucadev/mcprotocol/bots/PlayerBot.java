package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.game.entity.player.Player;
import com.lucadev.mcprotocol.game.world.World;

/**
 * Most common implementation of Bot used.
 * PlayerBot acts as a regular player to the server and allows you to log in, chat and execute various other tasks.
 * This can be seen as the most feature-rich implementation of Bot.
 *
 * This implementation inherits the ChatBot functionality
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see Bot
 */
public class PlayerBot extends ChatBot {

    private Player player;
    private World world;

    /**
     * Construct a new bots using the given config.
     * Requests a secure connection when setting up the connection object.
     *
     * @param botBuilder the builder contains all required config to setup the bots.
     */
    public PlayerBot(BotBuilder botBuilder) {
        super(botBuilder);
    }

    /**
     * @return World that the player currently exists in.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Sets the world that the player exists in. Might need to change due to world teleportation.
     * @param world the world the player currently exists in.
     */
    public void setWorld(World world) {
        this.world = world;
    }
}
