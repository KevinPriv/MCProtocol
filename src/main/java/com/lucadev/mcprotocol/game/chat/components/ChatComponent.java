package com.lucadev.mcprotocol.game.chat.components;

import com.lucadev.mcprotocol.game.chat.event.ClickEvent;
import com.lucadev.mcprotocol.game.chat.event.HoverEvent;
import com.lucadev.mcprotocol.game.chat.styling.ChatColor;
import com.lucadev.mcprotocol.game.chat.styling.ChatStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Minecraft chat message component
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class ChatComponent {

    /**
     * Chat style
     */
    private List<ChatStyle> styles = new ArrayList<>();

    private ChatColor color = ChatColor.NONE;

    /**
     * Insertion string
     */
    private String insertion;

    /**
     * Click event
     */
    private ClickEvent clickEvent;

    /**
     * Hover event
     */
    private HoverEvent hoverEvent;

    /**
     * Sibling nodes
     */
    private List<ChatComponent> extra = new ArrayList<>();

    /**
     * Get the text from the component
     *
     * @return
     */
    public abstract String getText();

    /**
     * Gets the complete text without any styling.
     *
     * @return
     */
    public String getCompleteText() {
        if (extra == null) {

            return getText();
        }
        StringBuilder sb = new StringBuilder(getText());
        for (ChatComponent component : extra) {
            sb.append(component.getText());
        }
        return sb.toString();
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public void addExtra(ChatComponent component) {
        extra.add(component);
    }

    public void removeExtra(ChatComponent component) {
        extra.remove(component);
    }


    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public void setClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    public void setHoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }

    public void setExtra(List<ChatComponent> extra) {
        this.extra = extra;
    }

    public List<ChatStyle> getStyles() {
        return styles;
    }

    public void addStyle(ChatStyle style) {
        styles.add(style);
    }

    public void setStyles(List<ChatStyle> styles) {
        this.styles = styles;
    }

    public String getInsertion() {
        return insertion;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public List<ChatComponent> getExtra() {
        return extra;
    }
}
