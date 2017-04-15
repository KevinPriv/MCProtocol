package com.lucadev.mcprotocol.game.chat.event.action;

/**
 * Hover action
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum HoverAction {

    /**
     * Display text
     */
    SHOW_TEXT,

    /**
     * Dislay NBT Item.
     */
    SHOW_ITEM,

    /**
     * Display entity
     */
    SHOW_ENTITY,

    /**
     * Display achievement
     */
    SHOW_ACHIEVEMENT;

    public static HoverAction getAction(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    public String getName() {
        return name().toLowerCase();
    }

}
