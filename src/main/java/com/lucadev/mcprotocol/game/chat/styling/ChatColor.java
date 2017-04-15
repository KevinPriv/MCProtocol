package com.lucadev.mcprotocol.game.chat.styling;

/**
 * Minecraft chat colors. http://wiki.vg/Chat#Styles
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum ChatColor {
    BLACK("0"),
    DARK_BLUE("1"),
    DARK_GREEN("2"),
    DARK_AQUA("3"),
    DARK_RED("4"),
    DARK_PURPLE("5"),
    GOLD("6"),
    GRAY("7"),
    DARK_GRAY("8"),
    BLUE("9"),
    GREEN("a"),
    AQUA("b"),
    RED("c"),
    LIGHT_PURPLE("d"),
    YELLOW("e"),
    WHITE("f"),
    NONE("");

    private String code;

    ChatColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ChatColor getColor(String color) {
        color = color.toUpperCase().replace(" ", "_");
        return valueOf(color);
    }

    public static ChatColor getColorByCode(String code) {
        code = code.toLowerCase();
        for (ChatColor chatColor : values()) {
            if (chatColor.getCode().equalsIgnoreCase(code)) {
                return chatColor;
            }
        }
        return ChatColor.NONE;
    }

    public String getName() {
        return name().toLowerCase();
    }


}
