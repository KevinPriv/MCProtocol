package com.lucadev.mcprotocol.auth;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class AuthException extends IOException {

    public AuthException() {
    }

    public AuthException(String s) {
        super(s);
    }

    public AuthException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AuthException(Throwable throwable) {
        super(throwable);
    }

}
