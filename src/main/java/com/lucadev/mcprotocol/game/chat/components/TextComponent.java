package com.lucadev.mcprotocol.game.chat.components;

/**
 * Simplest implementation of a chat component. Simply contains a text field.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.components.ChatComponent
 * @see <a href="http://wiki.vg/Chat">Wiki.vg chat article</a>
 */
public class TextComponent extends ChatComponent {

    private String text;

    /**
     * Setup the text component with the given text.
     * @param text the text value of the text component
     */
    public TextComponent(String text) {
        setText(text);
    }

    /**
     * @return as text component we return the text that was set.
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Set the text in the text component.
     * @param text the text to use as new value.
     */
    public void setText(String text) {
        this.text = text;
    }
}
