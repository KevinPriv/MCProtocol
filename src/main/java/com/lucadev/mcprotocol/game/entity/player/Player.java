package com.lucadev.mcprotocol.game.entity.player;

import com.lucadev.mcprotocol.bots.AbstractPlayBot;
import com.lucadev.mcprotocol.game.Difficulty;
import com.lucadev.mcprotocol.game.Dimension;
import com.lucadev.mcprotocol.game.GameMode;
import com.lucadev.mcprotocol.game.PlayerAbilities;
import com.lucadev.mcprotocol.protocol.Protocol;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Player extends OtherPlayer {

    GameMode getGameMode();

    Dimension getDimension();

    Difficulty getDifficulty();

    String getLevelType();

    boolean showReducedDebugInfo();

    void setDifficulty(Difficulty difficulty);

    PlayerAbilities getPlayerAbilities();

    void setPlayerAbilities(PlayerAbilities playerAbilities);

    void setOnGround(boolean onground);
}
