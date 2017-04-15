package com.lucadev.mcprotocol.game.chat.event.action;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum ClickAction {
    //Opens specified url
    OPEN_URL,

    //run command
    RUN_COMMAND,

    //suggest command
    SUGGEST_COMMAND,

    //changes page in a book
    CHANGE_PAGE;

    public static ClickAction getAction(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    public String getName() {
        return name().toLowerCase();
    }

}
