package com.lucadev.mcprotocol.game.chat.event;

import com.lucadev.mcprotocol.game.chat.event.action.ClickAction;

/**
 * Event that can happen when a chat component is clicked on.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ClickEvent {

    private ClickAction action;
    private String value;

    /**
     * Constructs a click event
     * @param action the action to take when clicked on
     * @param value the information that the action may consume if wanted.
     */
    public ClickEvent(ClickAction action, String value) {
        this.action = action;
        this.value = value;
    }

    /**
     * @return Action to take when clicked
     */
    public ClickAction getAction() {
        return action;
    }

    /**
     * @return Information that can be consumed by the action if required(such as the url to open)
     */
    public String getValue() {
        return value;
    }
}
