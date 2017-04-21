package com.lucadev.mcprotocol.game.chat.event.action;

/**
 * Enum that will be used to identify what action to tak on a click event.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.event.ClickEvent
 */
public enum ClickAction {
    /**
     * Open a URL
     */
    OPEN_URL,

    /**
     * Send a command to the server
     */
    RUN_COMMAND,

    /**
     * Suggests a command to execute
     */
    SUGGEST_COMMAND,

    /**
     * Change a page in a Minecraft book
     */
    CHANGE_PAGE;

    /**
     * Similar to valueOf but this first makes the name all uppercase to resolve the enum.
     *
     * @param name the name of the action.
     * @return the enum associated with the given name.
     */
    public static ClickAction getAction(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    /**
     * @return Get the minecraft name of the action which is the enum name in lower case.
     */
    public String getName() {
        return name().toLowerCase();
    }

}
