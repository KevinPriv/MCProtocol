package com.lucadev.mcprotocol.game.chat;

import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.game.chat.components.TextComponent;

/**
 * Some help methods to make chatting easier
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ChatHelper {

    public static final ChatComponent simpleChatMessage(String text) {
        return new TextComponent(text);
    }
}
