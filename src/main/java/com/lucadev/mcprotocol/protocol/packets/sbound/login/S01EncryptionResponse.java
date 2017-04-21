package com.lucadev.mcprotocol.protocol.packets.sbound.login;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;
import com.lucadev.mcprotocol.util.EncryptionUtil;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S01EncryptionResponse extends AbstractPacket implements WritablePacket {

    private byte[] sharedSecret;
    private byte[] verifyToken;

    public S01EncryptionResponse(PublicKey pubKey, SecretKey secKey, byte[] verifyToken) {
        try {
            sharedSecret = EncryptionUtil.cipher(1, pubKey, secKey.getEncoded());
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
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeVarInt(sharedSecret.length);
        buff.write(sharedSecret);
        buff.writeVarInt(verifyToken.length);
        buff.write(verifyToken);
    }
}
