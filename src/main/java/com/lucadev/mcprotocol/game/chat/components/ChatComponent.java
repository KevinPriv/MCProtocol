package com.lucadev.mcprotocol.game.chat.components;

import com.lucadev.mcprotocol.game.chat.event.ClickEvent;
import com.lucadev.mcprotocol.game.chat.event.HoverEvent;
import com.lucadev.mcprotocol.game.chat.styling.ChatColor;
import com.lucadev.mcprotocol.game.chat.styling.ChatStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Minecraft chat component. These components are encoded and decoded to and from json sent by the client or server.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see <a href="http://wiki.vg/Chat">Wiki.vg chat article</a>
 */
public abstract class ChatComponent {

    private List<ChatStyle> styles = new ArrayList<>();
    private ChatColor color = ChatColor.NONE;
    private String insertion;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;
    private List<ChatComponent> extra = new ArrayList<>();

    /**
     * @return text information from the component.
     */
    public abstract String getText();

    /**
     * @return the text from this component and appended text from all sibling components. Used to form a complete message.
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

    /**
     * @return color that the component should use for rendering.
     * @see com.lucadev.mcprotocol.game.chat.styling.ChatColor
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     * Sets the color for the component
     * @param color one of the chatcolors available.
     * @see com.lucadev.mcprotocol.game.chat.styling.ChatColor
     */
    public void setColor(ChatColor color) {
        this.color = color;
    }

    /**
     * Add a sibling component to this component.
     * @param component the new sibling component.
     */
    public void addExtra(ChatComponent component) {
        extra.add(component);
    }

    /**
     * Remove a sibling component.
     * Does not check if it's a valid sibling component.
     * @param component the sibling component to remove from this component
     */
    public void removeExtra(ChatComponent component) {
        extra.remove(component);
    }

    /**
     * Sets the text to insert. Only used for messages in chat.
     * When shift is held, clicking the component inserts the given text into the chat box at the cursor (potentially replacing selected text).
     * Has no effect on other locations at this time.
     * @param insertion the text to insert when this component is clicked on.
     */
    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    /**
     * Set the event that should take place when the component is clicked on.
     * @param clickEvent the event that will take place.
     */
    public void setClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    /**
     * Set the event that should take place when the component is hovered over(with the mouse).
     * @param hoverEvent the event that will take place.
     */
    public void setHoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }

    /**
     * Set the list of sibling components for this component.
     * @param extra list of components that will be seen as sibling components for this component.
     */
    public void setExtra(List<ChatComponent> extra) {
        this.extra = extra;
    }

    /**
     * @return get a list of chat styles that are enabled on this component.
     * @see com.lucadev.mcprotocol.game.chat.styling.ChatStyle
     */
    public List<ChatStyle> getStyles() {
        return styles;
    }

    /**
     * Adds a style to the component
     * @param style the style to add to the component
     * @see com.lucadev.mcprotocol.game.chat.styling.ChatStyle
     */
    public void addStyle(ChatStyle style) {
        styles.add(style);
    }

    /**
     * Sets the list of styles that are enabled on this component
     * @param styles the styles to use for this component.
     */
    public void setStyles(List<ChatStyle> styles) {
        this.styles = styles;
    }

    /**
     * @return text that can be inserted(or current selection replaced) when this component is clicked on(depends on other factors too)
     */
    public String getInsertion() {
        return insertion;
    }

    /**
     * @return Optional event when the component gets clicked on. Can be null.
     */
    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    /**
     * @return Optional event when the component gets hovered over. Can be null.
     */
    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    /**
     * @return list of sibling components.
     */
    public List<ChatComponent> getExtra() {
        return extra;
    }
}
