package com.lucadev.mcprotocol.exceptions;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class ProtocolException extends IOException {

    public ProtocolException() {
    }

    public ProtocolException(String s) {
        super(s);
    }

    public ProtocolException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProtocolException(Throwable throwable) {
        super(throwable);
    }
}
