package com.lucadev.mcprotocol.protocol.packets.sbound.handshake;

import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;
import com.lucadev.mcprotocol.util.EncryptionUtil;

import javax.crypto.SecretKey;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S01EncryptionResponse extends AbstractPacket implements WritablePacket {

    private SecretKey sharedKey;
    private byte[] sharedSecret;
    private byte[] verifyToken;

    public S01EncryptionResponse(PublicKey pubKey, SecretKey secKey, byte[] verifyToken) {
        this.sharedKey = secKey;
        try {
            sharedSecret = EncryptionUtil.cipher(1, pubKey, sharedKey.getEncoded());
            this.verifyToken = EncryptionUtil.cipher(1, pubKey, verifyToken);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return 0x01;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, sharedSecret.length);
        os.write(sharedSecret);
        writeVarInt(os, verifyToken.length);
        os.write(verifyToken);
    }
}
