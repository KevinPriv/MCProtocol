package com.lucadev.mcprotocol.game.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.exceptions.ParseException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ChatConverter {

    /**
     * Parses the given objectnode into a valid chat component
     * @param objectNode
     * @return
     * @throws ParseException
     */
    ChatComponent parse(JsonNode objectNode) throws ParseException;

    /**
     * Parses the given json string to a chatnode.
     * @param json
     * @return
     * @throws ParseException
     */
    ChatComponent parse(String json) throws ParseException;

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
