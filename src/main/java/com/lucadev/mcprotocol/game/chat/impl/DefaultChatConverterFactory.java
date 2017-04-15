package com.lucadev.mcprotocol.game.chat.impl;

import com.lucadev.mcprotocol.game.chat.ChatConverter;
import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;

/**
 * Default factory used to create chat converters.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.ChatConverter
 */
public class DefaultChatConverterFactory implements ChatConverterFactory {

    private ChatConverter parser;

    /**
     * This method uses the singleton design pattern.
     * Once the converter has been created future calls to this method will return the same converter.
     * @return a chat converter.
     */
    @Override
    public ChatConverter createConverter() {
        if (parser == null) {
            parser = new DefaultChatConverter();
        }
        return parser;
    }
}
