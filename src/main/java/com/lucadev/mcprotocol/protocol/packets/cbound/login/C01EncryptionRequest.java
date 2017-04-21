package com.lucadev.mcprotocol.protocol.packets.cbound.login;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C01EncryptionRequest extends AbstractPacket implements ReadablePacket {

    /**
     * 20 char server id. Can be empty.
     */
    private String serverId;

    /**
     * VarInt size of pub key
     */
    private int pubKeyLength;

    /**
     * Pub key with pubKeyLength size
     */
    private byte[] pubKey;

    /**
     * VarInt to verify token
     */
    private int verifyTokenLength;

    /**
     * Verify token
     */
    private byte[] verifyToken;

    @Override
    public int getId() {
        return 0x01;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        serverId = buff.readVarString(20);
        pubKeyLength = buff.readVarInt();
        pubKey = new byte[pubKeyLength];
        buff.readFully(pubKey);
        verifyTokenLength = buff.readVarInt();
        verifyToken = new byte[verifyTokenLength];
        buff.readFully(verifyToken);
        System.out.println(toString());
    }

    public String getServerId() {
        return serverId;
    }

    public int getPubKeyLength() {
        return pubKeyLength;
    }

    public byte[] getPubKey() {
        return pubKey;
    }

    public int getVerifyTokenLength() {
        return verifyTokenLength;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public String toString() {
        return "C01EncryptionRequest{" +
                "serverId='" + serverId + '\'' +
                ", pubKeyLength=" + pubKeyLength +
                ", pubKey=" + Arrays.toString(pubKey) +
                ", verifyTokenLength=" + verifyTokenLength +
                ", verifyToken=" + Arrays.toString(verifyToken) +
                '}';
    }
}
