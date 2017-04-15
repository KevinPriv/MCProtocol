package com.lucadev.mcprotocol.protocol;

import java.io.IOException;

/**
 * Exception that gets thrown when something in the protocol goes wrong.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see java.io.IOException
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
