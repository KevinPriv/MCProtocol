package com.lucadev.mcprotocol.game.chat.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lucadev.mcprotocol.game.chat.ChatConverter;
import com.lucadev.mcprotocol.game.chat.ChatParseException;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.game.chat.components.ScoreComponent;
import com.lucadev.mcprotocol.game.chat.components.TextComponent;
import com.lucadev.mcprotocol.game.chat.components.TranslationComponent;
import com.lucadev.mcprotocol.game.chat.event.ClickEvent;
import com.lucadev.mcprotocol.game.chat.event.HoverEvent;
import com.lucadev.mcprotocol.game.chat.event.action.ClickAction;
import com.lucadev.mcprotocol.game.chat.event.action.HoverAction;
import com.lucadev.mcprotocol.game.chat.styling.ChatColor;
import com.lucadev.mcprotocol.game.chat.styling.ChatStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of ChatConverter
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.ChatConverter
 */
public class DefaultChatConverter implements ChatConverter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultChatConverter.class);

    /**
     * Parses the given json node into a valid chat component
     *
     * @param jsonNode parsed json node
     * @return chat component that was built from the jsonNode data
     * @throws ChatParseException gets thrown when something goes wrong parsing the json.
     */
    @Override
    public ChatComponent parse(JsonNode jsonNode) throws ChatParseException {
        ChatComponent component;
        if (jsonNode.has("text")) {
            //text component
            component = new TextComponent(jsonNode.get("text").textValue());
        } else if (jsonNode.has("translate")) {
            //translate component
            String key = jsonNode.get("translate").textValue();
            List<ChatComponent> params = new ArrayList<>();
            if (jsonNode.has("with")) {
                for (JsonNode comp : jsonNode.get("with")) {
                    params.add(parse(comp));
                }
            }
            component = new TranslationComponent(key, params.toArray(new ChatComponent[params.size()]));
        } else if (jsonNode.has("score")) {
            //score component
            JsonNode score = jsonNode.get("score");
            String name = score.get("name").textValue();
            String objective = score.get("objective").textValue();
            String value = null;
            if (score.has("value")) {
                value = score.get("value").textValue();
            }
            component = new ScoreComponent(name, objective, value);
        } else {
            component = new ChatComponent() {
                @Override
                public String getText() {
                    return "";
                }
            };
        }

        if (jsonNode.has("color")) {
            component.setColor(ChatColor.getColor(jsonNode.get("color").textValue()));
        }

        for (ChatStyle style : ChatStyle.values()) {
            if (jsonNode.has(style.getName()) && Boolean.parseBoolean(jsonNode.get(style.getName()).textValue())) {
                component.addStyle(style);
            }
        }

        if (jsonNode.has("insertion")) {
            component.setInsertion(jsonNode.get("insertion").textValue());
        }

        if (jsonNode.has("clickEvent")) {
            JsonNode clickNode = jsonNode.get("clickEvent");
            ClickAction action = ClickAction.getAction(clickNode.get("action").textValue());
            String value = clickNode.get("value").textValue();
            component.setClickEvent(new ClickEvent(action, value));
        }

        if (jsonNode.has("hoverEvent")) {
            JsonNode hoverNode = jsonNode.get("hoverEvent");
            HoverAction action = HoverAction.getAction(hoverNode.get("action").textValue());
            String value = hoverNode.get("value").textValue();
            component.setHoverEvent(new HoverEvent(action, value));
        }

        if (jsonNode.has("extra")) {
            JsonNode extras = jsonNode.get("extra");
            for (JsonNode compNode : extras) {
                ChatComponent ex = parse(compNode);
                component.addExtra(ex);
            }

        }

        return component;
    }

    /**
     * Parses the given json string to a chat component
     *
     * @param json string that contains the json for a chatcomponent.
     * @return a chatcomponent built from the data found in the given json.
     * @throws ChatParseException gets thrown when something goes wrong parsing the json.
     */
    @Override
    public ChatComponent parse(String json) throws ChatParseException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return parse(mapper.readTree(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts a chat component into their json formatted representation.
     *
     * @param component the component to parse to json.
     * @return json node with the information of the given chat component.
     */
    @Override
    public JsonNode toJson(ChatComponent component) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        if (component instanceof TextComponent) {
            node.put("text", component.getText());
        } else if (component instanceof TranslationComponent) {
            TranslationComponent translation = (TranslationComponent) component;
            node.put("translate", translation.getTranslationKey());
            if (translation.getTranslationParameters() != null && translation.getTranslationParameters().length > 0) {
                ArrayNode array = node.putArray("with");
                for (ChatComponent comp : translation.getTranslationParameters()) {
                    array.add(toJson(comp));
                }
            }
        } else if (component instanceof ScoreComponent) {
            ScoreComponent score = (ScoreComponent) component;
            ObjectNode scoreNode = node.putObject("score");
            scoreNode.put("name", score.getName());
            scoreNode.put("objective", score.getObjective());
            if (score.getValue() != null && !score.getValue().isEmpty()) {
                scoreNode.put("value", score.getValue());
            }
        } else {
            throw new IllegalStateException("UWeenknown chat component, cannot convert to json!");
        }
        if (component.getColor() != ChatColor.NONE)
            node.put("color", component.getColor().getName());

        if (component.getInsertion() != null && !component.getInsertion().isEmpty())
            node.put("insertion", component.getInsertion());

        if (component.getClickEvent() != null) {
            ClickEvent event = component.getClickEvent();
            ObjectNode clickNode = node.putObject("clickEvent");
            clickNode.put("action", event.getAction().getName());
            clickNode.put("value", event.getValue());
        }

        if (component.getHoverEvent() != null) {
            HoverEvent event = component.getHoverEvent();
            ObjectNode hoverNode = node.putObject("hoverEvent");
            hoverNode.put("action", event.getAction().getName());
            hoverNode.put("value", event.getValue());
        }

        for (ChatStyle chatStyle : component.getStyles()) {
            node.put(chatStyle.getName(), "true");
        }

        if (component.getExtra().size() > 0) {
            ArrayNode siblings = node.putArray("extra");
            for (ChatComponent ex : component.getExtra()) {
                siblings.add(toJson(ex));
            }
        }
        return node;
    }

    /**
     * Converts a chat component into their json formatted representation.
     *
     * @param chatComponent the component to parse to json.
     * @return json node with the information of the given chat component.
     */
    @Override
    public String toJsonString(ChatComponent chatComponent) {
        return toJson(chatComponent).asText();
    }
}
