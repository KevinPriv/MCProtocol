package com.lucadev.mcprotocol.game.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ChatConverter {

    /**
     * Parses the given objectnode into a valid chat component
     * @param objectNode
     * @return
     * @throws ChatParseException
     */
    ChatComponent parse(JsonNode objectNode) throws ChatParseException;

    /**
     * Parses the given json string to a chatnode.
     * @param json
     * @return
     * @throws ChatParseException
     */
    ChatComponent parse(String json) throws ChatParseException;

    /**
     * Convert object to json
     * @param chatComponent
     * @return
     */
    JsonNode toJson(ChatComponent chatComponent);

    /**
     * Convert object to json
     * @param chatComponent
     * @return
     */
    String toJsonString(ChatComponent chatComponent);
}
