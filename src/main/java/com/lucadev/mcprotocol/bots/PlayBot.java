package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.game.entity.player.Player;

/**
 * Interface offering more decoupling functionality for bots.
 * Extends functionality of ServerLogin and SessionAuthentication which are all required,
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface PlayBot extends Bot, ServerLogin, SessionAuthentication {

    /**
     * Player model once logged into the server.
     * @return
     */
    Player getPlayer();
}
