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
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class MCBrandPluginChannel extends PluginChannel {

    private Logger logger = LoggerFactory.getLogger(MCBrandPluginChannel.class);

    private String clientName = "vanilla";

    /**
     * Constructor setting the channel name
     */
    public MCBrandPluginChannel() {
        super("MC|Brand");
    }

    /**
     * Handle the bytes
     *
     * @param is
     */
    @Override
    public void readPayload(DataInputStream is) throws IOException {
        String clientName = readString(is);
        logger.info("Received server implementation: " + clientName);
    }

    /**
     * Data to write
     *
     * @param dos
     */
    @Override
    public void registerToServer(DataOutputStream dos) throws IOException {
        writeString(dos, clientName);
    }

}
