package com.lucadev.mcprotocol.game.chat.components;

/**
 * Component that can translate stuff.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.game.chat.components.ChatComponent
 * @see <a href="http://wiki.vg/Chat#Translation_Component">Wiki.vg Translation Component</a>
 */
public class TranslationComponent extends ChatComponent {

    private String translationKey;
    private ChatComponent[] translationParameters;

    /**
     * Sets up the translation component with the required data.
     *
     * @param key    translation key
     * @param params components that will be used as arguments for the translation.
     * @see <a href="http://wiki.vg/Chat#Translation_Component">Wiki.vg Translation Component</a>
     */
    public TranslationComponent(String key, ChatComponent[] params) {
        this.translationKey = key;
        this.translationParameters = params;
    }


    /**
     * @return translation components return their translation key as text.
     */
    @Override
    public String getText() {
        return translationKey;
    }

    /**
     * @return key used to translate other strings.
     */
    public String getTranslationKey() {
        return translationKey;
    }

    /**
     * Parameters to insert into text using the translation formatting.
     *
     * @return parameter components used for translation using the formatting format.
     * @see <a href="http://wiki.vg/Chat#Translation_Component">Wiki.vg Translation Component</a>
     */
    public ChatComponent[] getTranslationParameters() {
        return translationParameters;
    }

    /**
     * Sets the key used for translation.
     *
     * @param translationKey the key to use.
     */
    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    /**
     * Set the parameters used by the key to manage translations.
     *
     * @param translationParameters array of components
     */
    public void setTranslationParameters(ChatComponent[] translationParameters) {
        this.translationParameters = translationParameters;
    }
}
