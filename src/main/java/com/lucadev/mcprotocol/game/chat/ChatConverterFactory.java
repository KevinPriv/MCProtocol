package com.lucadev.mcprotocol.game.chat;

/**
 * Factory for creating chat converters used to read and write chat components
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ChatConverterFactory {

    /**
     * @return a chat converter.
     */
    ChatConverter createConverter();

}
