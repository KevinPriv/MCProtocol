package com.lucadev.mcprotocol.game.chat.components;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class TranslationComponent extends ChatComponent {

    /**
     * Translate key
     */
    private String translationKey;

    /**
     * Translation params
     */
    private ChatComponent[] translationParameters;

    public TranslationComponent(String key, ChatComponent[] params) {
        this.translationKey = key;
        this.translationParameters = params;
    }


    /**
     * Get the text from the component
     *
     * @return
     */
    @Override
    public String getText() {
        return translationKey;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public ChatComponent[] getTranslationParameters() {
        return translationParameters;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public void setTranslationParameters(ChatComponent[] translationParameters) {
        this.translationParameters = translationParameters;
    }
}
