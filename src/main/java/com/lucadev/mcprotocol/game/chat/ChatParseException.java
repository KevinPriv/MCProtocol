package com.lucadev.mcprotocol.game.chat;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ChatParseException extends Exception {
    public ChatParseException() {
    }

    public ChatParseException(String s) {
        super(s);
    }

    public ChatParseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ChatParseException(Throwable throwable) {
        super(throwable);
    }

    public ChatParseException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
