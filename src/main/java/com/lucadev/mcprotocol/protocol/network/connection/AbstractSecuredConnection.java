package com.lucadev.mcprotocol.protocol.network.connection;

import com.lucadev.mcprotocol.protocol.network.connection.impl.UnsecuredConnection;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Extends an unsecured connection and implements some basic functionality from the SecuredConnection interface.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractSecuredConnection extends UnsecuredConnection implements SecuredConnection{

    /**
     * Constructor which constructs the class to use the default socket factory.
     */
    public AbstractSecuredConnection() {
        super();
    }

    /**
     * @param socketFactory socket factory to use when creating a socket for the connection.
     */
    public AbstractSecuredConnection(SocketFactory socketFactory) {
        super(socketFactory);
    }

    /**
     * Secures both input and output streams.
     */
    @Override
    public void secure() {
        secureInput();
        secureOutput();
    }

    /**
     * @return connection secured both for input and output streams?
     */
    @Override
    public boolean isSecured() {
        return isInputSecured() && isOutputSecured();
    }

    /**
     * Sets the input stream for the connection.
     *
     * @param dataInputStream input stream to use.
     */
    @Override
    protected void setDataInputStream(DataInputStream dataInputStream) {
        //Do not allow the input to change after the connection has been secured.
        //We may not know if the call contains a secured or insecure input stream.
        if(isInputSecured()) {
            throw new IllegalStateException("May not set input stream after input has been secured.");
        }
        super.setDataInputStream(dataInputStream);
    }

    /**
     * Sets the output stream for the connection
     *
     * @param dataOutputStream output stream to use
     */
    @Override
    protected void setDataOutputStream(DataOutputStream dataOutputStream) {
        //Do not allow output to change after it has been secured.
        //We may not know if the call contains a secured on insecure output stream.
        if(isOutputSecured()) {
            throw new IllegalStateException("May not set output stream after output has been secured.");
        }
        super.setDataOutputStream(dataOutputStream);
    }
}
