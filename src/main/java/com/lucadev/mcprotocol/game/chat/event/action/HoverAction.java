package com.lucadev.mcprotocol.game.chat.event.action;

/**
 * Enum that is used to specify what action to take when a hover event happens.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.event.HoverEvent
 */
public enum HoverAction {

    /**
     * Display a piece of text
     */
    SHOW_TEXT,

    /**
     * Display an item NBT
     */
    SHOW_ITEM,

    /**
     * Display entity NBT
     */
    SHOW_ENTITY,

    /**
     * Display achievement NBT
     */
    SHOW_ACHIEVEMENT;

    /**
     * Similar to valueOf but first makes all letters of name uppercase
     *
     * @param name name of the action
     * @return corresponding enum
     */
    public static HoverAction getAction(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    /**
     * Name of the action used by minecraft self. Simply the enum name to lowercase
     *
     * @return
     */
    public String getName() {
        return name().toLowerCase();
    }

}
