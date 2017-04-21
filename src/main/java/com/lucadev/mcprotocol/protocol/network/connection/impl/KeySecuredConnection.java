package com.lucadev.mcprotocol.protocol.network.connection.impl;

import com.lucadev.mcprotocol.protocol.network.connection.AbstractSecuredConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.util.EncryptionUtil.decryptInputStream;
import static com.lucadev.mcprotocol.util.EncryptionUtil.encryptOutputStream;

/**
 * Implementation of a secured connection that makes uses AES/CFB8 cipher streams used by Minecraft.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class KeySecuredConnection extends AbstractSecuredConnection {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(KeySecuredConnection.class);

    private SecretKey sharedKey;
    private boolean encrypting = false;
    private boolean decrypting = false;

    /**
     * Constructor which constructs the class to use the default socket factory.
     */
    public KeySecuredConnection() {
        super();
    }

    /**
     * @param socketFactory socket factory to use when creating a socket for the connection.
     */
    public KeySecuredConnection(SocketFactory socketFactory) {
        super(socketFactory);
    }

    /**
     * @return crypto key used for securing the connection.
     */
    public SecretKey getSharedKey() {
        return sharedKey;
    }

    /**
     * Sets the crypto key to use for securing the connection.
     * This method will only allow you to set the key once. Else it will throw an IllegalStateException
     *
     * @param sharedKey the crypto key to secure the connection.
     */
    public void setSharedKey(SecretKey sharedKey) {
        if (getSharedKey() != null) {
            throw new IllegalStateException("May not set connection security key more than once.");
        }
        this.sharedKey = sharedKey;
    }

    /**
     * Enable a secured input stream.
     */
    @Override
    public void secureInput() {
        if (!isConnected()) {
            throw new IllegalStateException("Connection needs to be connected before securing input.");
        }
        if (getSharedKey() == null) {
            throw new IllegalStateException("Crypto key required before securing input.");
        }
        if (isInputSecured()) {
            throw new IllegalStateException("Input already secured. May not secure twice.");
        }
        //AES/CFB8 cipher implemented in the decryptInputStream method from EncryptionUtil
        setDataInputStream(new DataInputStream(decryptInputStream(getDataInputStream(), getSharedKey())));
        decrypting = true;
        logger.info("Secured connection input");
    }

    /**
     * Enable a secured output stream.
     */
    @Override
    public void secureOutput() {
        if (!isConnected()) {
            throw new IllegalStateException("Connection needs to be connected before securing output.");
        }
        if (getSharedKey() == null) {
            throw new IllegalStateException("Crypto key required before securing output.");
        }
        if (isOutputSecured()) {
            throw new IllegalStateException("Output already secured. May not secure twice.");
        }
        //AES/CFB8 cipher implemented in encryptOutputStream method from EncryptionUtil
        setDataOutputStream(new DataOutputStream(encryptOutputStream(getDataOutputStream(), getSharedKey())));
        encrypting = true;
        logger.info("Secured connection output");
    }

    /**
     * @return secured the input stream
     */
    @Override
    public boolean isInputSecured() {
        return decrypting;
    }

    /**
     * @return secured the output stream
     */
    @Override
    public boolean isOutputSecured() {
        return encrypting;
    }

    /**
     * Closes the socket and streams immediately.
     *
     * @throws IOException when something goes wrong closing the connection.
     */
    @Override
    public void close() throws IOException {
        super.close();
        encrypting = false;
        decrypting = false;
        sharedKey = null;
    }
}
