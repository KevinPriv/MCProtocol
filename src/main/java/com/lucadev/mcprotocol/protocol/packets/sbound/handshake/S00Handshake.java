package com.lucadev.mcprotocol.protocol.packets.sbound.handshake;

import com.lucadev.mcprotocol.protocol.State;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

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
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeVarInt(protocolVersion);
        buff.writeVarString(host);
        buff.writeShort(port);
        buff.writeVarInt(state.getStateCode());
    }
}


