package com.lucadev.mcprotocol.game.chat;

import java.io.IOException;

/**
 * Simple custom exception that gets thrown whenever we cannot parse a chat component
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ChatParseException extends IOException {
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

}
