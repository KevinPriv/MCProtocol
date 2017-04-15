package com.lucadev.mcprotocol.auth.yggdrasil;

/**
 * Object with error info if something fails
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class AuthError {

    /**
     * Short error description.
     */
    private String message;
    /**
     * Longer description meant for the user
     */
    private String errorMessage;

    /**
     * Optional cause
     */
    private String cause;

    public AuthError() {
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCause() {
        return cause;
    }
}
