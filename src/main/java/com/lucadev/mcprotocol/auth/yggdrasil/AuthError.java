package com.lucadev.mcprotocol.auth.yggdrasil;

/**
 * Model of the json returned by Mojang auth servers if an error occurs during authentication.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class AuthError {

    private String error;
    private String errorMessage;
    private String cause;

    /**
     * Default constructor added to be sure that the json library we're using can create this object.
     */
    public AuthError() {
    }

    /**
     * @return Short description of the error.
     */
    public String getError() {
        return error;
    }

    /**
     * @return Longer description which can be shown to the user.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return Optional cause of the error. This string may be null or empty.
     */
    public String getCause() {
        return cause;
    }
}
