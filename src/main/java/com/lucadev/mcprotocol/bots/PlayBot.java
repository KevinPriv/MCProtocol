package com.lucadev.mcprotocol.bots;

/**
 * Interface offering more decoupling functionality for bots.
 * Extends functionality of ServerJoinable and SessionAuthentication which are all required,
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface PlayBot extends Bot, ServerJoinable, SessionAuthentication {
}
