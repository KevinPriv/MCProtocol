package com.lucadev.mcprotocol.game.chat.impl;

import com.lucadev.mcprotocol.game.chat.ChatConverter;
import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultChatConverterFactory implements ChatConverterFactory {

    private ChatConverter parser;

    @Override
    public ChatConverter createParser() {
        if(parser == null) {
            parser = new DefaultChatConverter();
        }
        return parser;
    }
}
