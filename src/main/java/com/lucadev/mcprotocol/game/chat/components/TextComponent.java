package com.lucadev.mcprotocol.game.chat.components;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class TextComponent extends ChatComponent {

    private String text;

    public TextComponent(String text) {
        setText(text);
    }

    /**
     * Get the text from the component
     *
     * @return
     */
    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
