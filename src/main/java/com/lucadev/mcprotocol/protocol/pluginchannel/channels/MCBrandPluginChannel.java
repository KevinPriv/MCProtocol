package com.lucadev.mcprotocol.protocol.pluginchannel.channels;

import com.lucadev.mcprotocol.protocol.pluginchannel.PluginChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;
import static com.lucadev.mcprotocol.protocol.VarHelper.writeString;

/**
 * Two-way plugin message to announce client/server implementation name.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class MCBrandPluginChannel extends PluginChannel {

    /**
     * System logger
     */
    private final static Logger logger = LoggerFactory.getLogger(MCBrandPluginChannel.class);

    /**
     * Client implementation name to send to the server.
     */
    private static final String CLIENT_IMPLEMENTATION = "vanilla";

    private String serverImplementation;

    /**
     * Constructor setting the channel name
     */
    public MCBrandPluginChannel() {
        super("MC|Brand");
    }

    /**
     * Handle custom data payload
     * @param is stream containing all payload data.
     * @throws IOException when something goes wrong while handling payload data.
     */
    @Override
    public void readPayload(DataInputStream is) throws IOException {
        serverImplementation = readString(is);
        logger.info("Received server implementation: " + serverImplementation);
    }

    /**
     * @return name of the server implementation.
     *          Null if this channel was not supported by the server or the channel hasn't been loaded yet.
     */
    public String getServerImplementationName() {
        return serverImplementation;
    }

    /**
     * When registering your channel to the server write your data payload through this method.
     * @param dos stream for custom data payload.
     * @throws IOException when something goes wrong while writing payload data.
     */
    @Override
    public void registerToServer(DataOutputStream dos) throws IOException {
        writeString(dos, CLIENT_IMPLEMENTATION);
    }

}
