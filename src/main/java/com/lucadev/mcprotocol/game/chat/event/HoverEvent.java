package com.lucadev.mcprotocol.game.chat.event;

import com.lucadev.mcprotocol.game.chat.actions.HoverAction;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class HoverEvent {

    private HoverAction action;
    private String value;

    public HoverEvent(HoverAction action, String value) {
        this.action = action;
        this.value = value;
    }

    public HoverAction getAction() {
        return action;
    }

    public String getValue() {
        return value;
    }
}
