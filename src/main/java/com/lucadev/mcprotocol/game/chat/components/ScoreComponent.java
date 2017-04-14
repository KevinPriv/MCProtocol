package com.lucadev.mcprotocol.game.chat.components;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ScoreComponent extends ChatComponent {

    /**
     * Player username or entity UUID
     */
    private String name;

    /**
     * Name of the objective
     */
    private String objective;

    /**
     * Resolved value of that objective.
     */
    private String value;

    public ScoreComponent(String name, String objective, String value) {
        this.name = name;
        this.objective = objective;
        this.value = value;
    }

    /**
     * Get the text from the component
     *
     * @return
     */
    @Override
    public String getText() {
        return objective;
    }

    public String getName() {
        return name;
    }

    public String getObjective() {
        return objective;
    }

    public String getValue() {
        return value;
    }
}
