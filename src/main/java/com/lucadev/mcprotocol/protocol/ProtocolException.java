package com.lucadev.mcprotocol.protocol;

/**
 * Exception that gets thrown when something in the protocol goes wrong.
 * Extends runtime exception since we cannot recover from protocol errors.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see java.lang.RuntimeException
 */
public class ProtocolException extends RuntimeException {

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

    public ProtocolException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
