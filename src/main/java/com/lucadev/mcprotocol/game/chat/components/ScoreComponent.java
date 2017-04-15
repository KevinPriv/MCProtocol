package com.lucadev.mcprotocol.game.chat.components;

/**
 * A chat component implementation to handle a score.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.components.ChatComponent
 * @see <a href="http://wiki.vg/Chat">Wiki.vg chat article</a>
 */
public class ScoreComponent extends ChatComponent {

    private String name;
    private String objective;
    private String value;

    /**
     * Setup a score component with the required score data.
     * @param name username if player, entity uuid if entity
     * @param objective object name
     * @param value resolved objective value
     */
    public ScoreComponent(String name, String objective, String value) {
        this.name = name;
        this.objective = objective;
        this.value = value;
    }

    /**
     * @return for score components we return the objective name as text
     */
    @Override
    public String getText() {
        return objective;
    }

    /**
     * @return username if player, entity uuid if entity.
     */
    public String getName() {
        return name;
    }

    /**
     * @return objective name
     */
    public String getObjective() {
        return objective;
    }

    /**
     * @return resolved objective value
     */
    public String getValue() {
        return value;
    }
}
