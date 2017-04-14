package com.lucadev.mcprotocol.protocol.packets.sbound.handshake;

import com.lucadev.mcprotocol.protocol.State;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeString;
import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S00Handshake extends AbstractPacket implements WritablePacket {

    private String host;
    private int port;
    private int protocolVersion;
    private State state;

    public S00Handshake(String host, int port, int protocolVersion, State state) {
        this.host = host;
        this.port = port;
        this.protocolVersion = protocolVersion;
        this.state = state;
    }

    public S00Handshake() {

    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    protected State getState() {
        return state;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, protocolVersion);
        writeString(os, host);
        os.writeShort(port);
        writeVarInt(os, state.getStateCode());
    }
}


