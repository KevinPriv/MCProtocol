package com.lucadev.mcprotocol.protocol.packet.cbound.login;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;
import static com.lucadev.mcprotocol.protocol.VarHelper.readVarInt;

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
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        serverId = readString(is, 20);
        pubKeyLength = readVarInt(is);
        pubKey = new byte[pubKeyLength];
        is.readFully(pubKey);
        verifyTokenLength = readVarInt(is);
        verifyToken = new byte[verifyTokenLength];
        is.readFully(verifyToken);
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
