package com.lucadev.mcprotocol.protocol.network.connection;

/**
 * Extension of the Connection interface which will offer connection security.
 * Once a stream has been secured it may not be unsecured.
 * The connection should function as a non-secure connection if no secure call has been made.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface SecuredConnection extends Connection {

    /**
     * Enable connection security for both in and output streams.
     * All exceptions should be handled inside this method.
     */
    void secure();

    /**
     * Enable a secured input stream.
     */
    void secureInput();

    /**
     * Enable a secured output stream.
     */
    void secureOutput();

    /**
     * @return connection secured both for input and output streams?
     */
    boolean isSecured();

    /**
     * @return secured the input stream
     */
    boolean isInputSecured();

    /**
     * @return secured the output stream
     */
    boolean isOutputSecured();
}
