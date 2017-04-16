package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.game.chat.ChatHandler;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.packets.cbound.play.C16ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Implementation of a bot that makes writing chat bots easy.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ChatBot extends AbstractPlayBot implements ChatHandler {

    public static final byte CHAT_POSITION_CHATBOX = 0;
    public static final byte CHAT_POSITION_SYSTEM_CHATBOX = 1;
    public static final byte CHAT_POSITION_HOTBAR = 2;

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ChatBot.class);


    /**
     * Construct a new bots using the given config.
     * Requests a secure connection when setting up the connection object.
     *
     * @param botBuilder the builder contains all required config to setup the bots.
     */
    public ChatBot(BotBuilder botBuilder) {
        super(botBuilder);
        getProtocol().registerPacketListener(C16ChatMessage.class, (bot, packet) -> {
            //we can safely cast since this has been type checked before the listener was fired.
            C16ChatMessage message = (C16ChatMessage) packet;
            handleIncomingChat(message.getChatComponent(), message.getPosition());
        });
    }

    /**
     * Send a chat message to the server.
     * @param message text message to send.
     * @throws IOException when we fail to send the message.
     */
    @Override
    public void sendChatMessage(String message) throws IOException {
        getProtocol().sendChatMessage(message);
    }

    /**
     * Handle incoming chat messages for the ChatHandler to further process.
     * @param component chat component
     * @param position chat messages are used for multiple things.
     *                 Position defines what the read component should be used for.
     *                 0: chat(chat box)
     *                 1: system message(chat box)
     *                 2: above hotbar
     */
    public void handleIncomingChat(ChatComponent component, byte position) {
        //just print chat messages until more functionality is added
        switch (position) {
            case CHAT_POSITION_SYSTEM_CHATBOX:
                //the position used for the ingame chatbox
                logger.info("CHAT: {}", component.getCompleteText());
                break;
            case CHAT_POSITION_CHATBOX:
                break;
            case CHAT_POSITION_HOTBAR:
                break;
        }
    }
}
