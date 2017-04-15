package com.lucadev.mcprotocol.auth;

import java.io.IOException;

/**
 * Basic exception that extends IOException.
 * This exception will be thrown when we fail to authenticate with a reason other than a normal IOException
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see java.io.IOException for more documentation.
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
