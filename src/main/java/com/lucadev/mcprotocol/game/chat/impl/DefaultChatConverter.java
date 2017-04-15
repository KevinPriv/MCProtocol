package com.lucadev.mcprotocol.game.chat.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lucadev.mcprotocol.game.chat.ChatConverter;
import com.lucadev.mcprotocol.game.chat.event.action.ClickAction;
import com.lucadev.mcprotocol.game.chat.event.action.HoverAction;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.game.chat.components.ScoreComponent;
import com.lucadev.mcprotocol.game.chat.components.TextComponent;
import com.lucadev.mcprotocol.game.chat.components.TranslationComponent;
import com.lucadev.mcprotocol.game.chat.event.ClickEvent;
import com.lucadev.mcprotocol.game.chat.event.HoverEvent;
import com.lucadev.mcprotocol.game.chat.styling.ChatColor;
import com.lucadev.mcprotocol.game.chat.styling.ChatStyle;
import com.lucadev.mcprotocol.game.chat.ChatParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class DefaultChatConverter implements ChatConverter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultChatConverter.class);

    /**
     * Parses the given objectnode into a valid chat component
     *
     * @param node
     * @return
     * @throws ChatParseException
     */
    @Override
    public ChatComponent parse(JsonNode node) throws ChatParseException {
        ChatComponent component;
        if(node.has("text")) {
            //text component
            component = new TextComponent(node.get("text").textValue());
        } else if (node.has("translate")) {
            //translate component
            String key = node.get("translate").textValue();
            List<ChatComponent> params = new ArrayList<>();
            if(node.has("with")) {
                for (JsonNode comp : node.get("with")) {
                    params.add(parse(comp));
                }
            }
            component = new TranslationComponent(key, params.toArray(new ChatComponent[params.size()]));
        } else if (node.has("score")) {
            //score component
            JsonNode score = node.get("score");
            String name = score.get("name").textValue();
            String objective = score.get("objective").textValue();
            String value = null;
            if(score.has("value")) {
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

        if(node.has("color")) {
            component.setColor(ChatColor.getColor(node.get("color").textValue()));
        }

        for (ChatStyle style : ChatStyle.values()) {
            if(node.has(style.getName()) && Boolean.parseBoolean(node.get(style.getName()).textValue())) {
                component.addStyle(style);
            }
        }

        if(node.has("insertion")) {
            component.setInsertion(node.get("insertion").textValue());
        }

        if(node.has("clickEvent")) {
            JsonNode clickNode = node.get("clickEvent");
            ClickAction action = ClickAction.getAction(clickNode.get("action").textValue());
            String value = clickNode.get("value").textValue();
            component.setClickEvent(new ClickEvent(action, value));
        }

        if(node.has("hoverEvent")) {
            JsonNode hoverNode = node.get("hoverEvent");
            HoverAction action = HoverAction.getAction(hoverNode.get("action").textValue());
            String value = hoverNode.get("value").textValue();
            component.setHoverEvent(new HoverEvent(action, value));
        }

        if(node.has("extra")) {
            JsonNode extras = node.get("extra");
            for (JsonNode compNode : extras) {
                ChatComponent ex = parse(compNode);
                component.addExtra(ex);
            }

        }

        return component;
    }

    /**
     * Parses the given json string to a chatnode.
     *
     * @param json
     * @return
     * @throws ChatParseException
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
     * Convert object to json
     *
     * @param component
     * @return
     */
    @Override
    public JsonNode toJson(ChatComponent component) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        if(component instanceof TextComponent) {
            node.put("text", component.getText());
        } else if(component instanceof TranslationComponent) {
            TranslationComponent translation = (TranslationComponent)component;
            node.put("translate", translation.getTranslationKey());
            if(translation.getTranslationParameters() != null && translation.getTranslationParameters().length > 0) {
                ArrayNode array = node.putArray("with");
                for (ChatComponent comp : translation.getTranslationParameters()) {
                    array.add(toJson(comp));
                }
            }
        } else if (component instanceof ScoreComponent) {
            ScoreComponent score = (ScoreComponent)component;
            ObjectNode scoreNode = node.putObject("score");
            scoreNode.put("name", score.getName());
            scoreNode.put("objective", score.getObjective());
            if(score.getValue() != null && !score.getValue().isEmpty()) {
                scoreNode.put("value", score.getValue());
            }
        } else {
            throw new IllegalStateException("Unknown chat component, cannot convert to json!");
        }
        if(component.getColor() != ChatColor.NONE)
            node.put("color", component.getColor().getName());

        if(component.getInsertion() != null && !component.getInsertion().isEmpty())
            node.put("insertion", component.getInsertion());

        if(component.getClickEvent() != null) {
            ClickEvent event = component.getClickEvent();
            ObjectNode clickNode = node.putObject("clickEvent");
            clickNode.put("action", event.getAction().getName());
            clickNode.put("value", event.getValue());
        }

        if(component.getHoverEvent() != null) {
            HoverEvent event = component.getHoverEvent();
            ObjectNode hoverNode = node.putObject("hoverEvent");
            hoverNode.put("action", event.getAction().getName());
            hoverNode.put("value", event.getValue());
        }

        for (ChatStyle chatStyle : component.getStyles()) {
            node.put(chatStyle.getName(), "true");
        }

        if(component.getExtra().size() > 0) {
            ArrayNode siblings = node.putArray("extra");
            for (ChatComponent ex : component.getExtra()) {
                siblings.add(toJson(ex));
            }
        }
        return node;
    }

    /**
     * Convert object to json
     *
     * @param chatComponent
     * @return
     */
    @Override
    public String toJsonString(ChatComponent chatComponent) {
        return toJson(chatComponent).asText();
    }
}
