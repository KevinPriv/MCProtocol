package com.lucadev.mcprotocol.game.chat;

import com.lucadev.mcprotocol.game.chat.components.ChatComponent;

import java.io.IOException;

/**
 * Interface that adds functionality to handle chat messages in and outbound.
 * TODO: chat listeners, callbacks and whatnot
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ChatHandler {

    /**
     * Send a chat message to the server.
     *
     * @param message text message to send.
     * @throws IOException when we fail to send the message.
     */
    void sendChatMessage(String message) throws IOException;

    /**
     * Handle incoming chat messages for the ChatHandler to further process.
     *
     * @param component chat component
     * @param position  chat messages are used for multiple things.
     *                  Position defines what the read component should be used for.
     *                  0: chat(chat box)
     *                  1: system message(chat box)
     *                  2: above hotbar
     */
    void handleIncomingChat(ChatComponent component, byte position);
}
