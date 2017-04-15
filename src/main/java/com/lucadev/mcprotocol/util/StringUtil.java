package com.lucadev.mcprotocol.util;

/**
 * Provides helping string manipulation methods that are not built into String itself or take up a lot of code if written each time;
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public final class StringUtil {

    /**
     * The constructor of the class is made private so no instances of it can be created from the outside.
     * This is done because we do not require an instance of StringUtil to function correctly.
     */
    private StringUtil() {

    }

    /**
     * Checks if the given string is equal to null or the contents of the given string are empty.
     *
     * @param str the string to check against.
     * @return the str parameter being null or empty of contents as boolean data type.
     * @see java.lang.String
     */
    public static boolean isNullOrEmpty(String str) {
        if(str == null) {
            return true;
        }

        if(str.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the given string is not equal to null and the contents of the given string are not empty.
     *
     * @param str the string to check against.
     * @return the str parameter check result given as boolean datatype.
     * @see java.lang.String
     */
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

}
