package com.lucadev.mcprotocol.game.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;

/**
 * Interface that contains the basic methods required to convert chat components from and to their json data representation.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface ChatConverter {

    /**
     * Parses the given json node into a valid chat component
     *
     * @param jsonNode parsed json node
     * @return chat component that was built from the jsonNode data
     * @throws ChatParseException gets thrown when something goes wrong parsing the json.
     */
    ChatComponent parse(JsonNode jsonNode) throws ChatParseException;

    /**
     * Parses the given json string to a chat component
     *
     * @param json string that contains the json for a chatcomponent.
     * @return a chatcomponent built from the data found in the given json.
     * @throws ChatParseException gets thrown when something goes wrong parsing the json.
     */
    ChatComponent parse(String json) throws ChatParseException;

    /**
     * Converts a chat component into their json formatted representation.
     *
     * @param chatComponent the component to parse to json.
     * @return json node with the information of the given chat component.
     */
    JsonNode toJson(ChatComponent chatComponent);

    /**
     * Converts a chat component into their json formatted representation.
     *
     * @param chatComponent the component to parse to json.
     * @return json node with the information of the given chat component.
     */
    String toJsonString(ChatComponent chatComponent);
}
