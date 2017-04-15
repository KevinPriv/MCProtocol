package com.lucadev.mcprotocol.game.chat.event;

import com.lucadev.mcprotocol.game.chat.event.action.ClickAction;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ClickEvent {

    private ClickAction action;

    private String value;

    public ClickEvent(ClickAction action, String value) {
        this.action = action;
        this.value = value;
    }

    public ClickAction getAction() {
        return action;
    }

    public String getValue() {
        return value;
    }
}
