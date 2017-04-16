package com.lucadev.mcprotocol.auth.yggdrasil;

import com.lucadev.mcprotocol.auth.AuthException;

/**
 * Extension to AuthException specifically for Yggdrasil exceptions.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class YggdrasilAuthException extends AuthException {

    private AuthError error;

    public YggdrasilAuthException() {
    }

    public YggdrasilAuthException(AuthError error) {
        this("Yggdrasil auth error: " + error.getErrorMessage() + " probable cause: " + error.getCause());
        this.error = error;
    }

    public YggdrasilAuthException(String s) {
        super(s);
    }

    public YggdrasilAuthException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public YggdrasilAuthException(Throwable throwable) {
        super(throwable);
    }

    public AuthError getError() {
        return error;
    }
}
