package com.lucadev.mcprotocol.game.chat;

import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.game.chat.components.TextComponent;

/**
 * Some help methods to make creating chat components easier.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.components.ChatComponent
 */
public class ChatHelper {

    /**
     * Private constructor since we do not want to be able to make instance of this class.
     */
    private ChatHelper() {

    }

    /**
     * Create a chat component from the given string
     * @param text the text that the message should contain.
     * @return a chat component that contains the given text.
     */
    public static final ChatComponent simpleChatMessage(String text) {
        return new TextComponent(text);
    }
}
