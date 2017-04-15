package com.lucadev.mcprotocol.game.chat.styling;

/**
 * Chat styles http://wiki.vg/Chat#Styles
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum ChatStyle {
    OBFUSCATED("k"),
    BOLD("l"),
    STRIKETHROUGH("m"),
    UNDERLINED("n"),
    ITALIC("o"),
    RESET("r");

    private String code;

    ChatStyle(String code) {
        this.code = code;
    }

    public static ChatStyle getStyle(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    public static ChatStyle getStyleByCode(String code) {
        code = code.toLowerCase();
        for (ChatStyle chatStyle : values()) {
            if (chatStyle.getCode().equals(code)) {
                return chatStyle;
            }
        }
        return ChatStyle.RESET;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name().toLowerCase();
    }

}
