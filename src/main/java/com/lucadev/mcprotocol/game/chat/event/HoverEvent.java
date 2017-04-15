package com.lucadev.mcprotocol.game.chat.event;

import com.lucadev.mcprotocol.game.chat.event.action.HoverAction;

/**
 * Event that can happen when a chat component is hovered over.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class HoverEvent {

    private HoverAction action;
    private String value;

    /**
     * Constructs a hover event
     * @param action the action to take when hovered over
     * @param value the information that the action may consume if wanted.
     */
    public HoverEvent(HoverAction action, String value) {
        this.action = action;
        this.value = value;
    }

    /**
     * @return action to take when hovered over.
     */
    public HoverAction getAction() {
        return action;
    }

    /**
     * @return Information that can be consumed by the action if required(such as the text to display)
     */
    public String getValue() {
        return value;
    }
}
