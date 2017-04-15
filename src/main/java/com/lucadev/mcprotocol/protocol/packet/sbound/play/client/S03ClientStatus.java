package com.lucadev.mcprotocol.protocol.packet.sbound.play.client;

import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.WritablePacket;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.writeVarInt;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class S03ClientStatus extends AbstractPacket implements WritablePacket {

    public enum ClientAction {
        PERFORM_RESPAWN(0),
        REQUEST_STATS(1),
        OPEN_INVENTORY(2);

        private int actionCode;

        ClientAction(int code) {
            actionCode = code;
        }

        public int getActionCode() {
            return actionCode;
        }


    }

    private ClientAction action;

    public S03ClientStatus(ClientAction action) {
        this.action = action;
    }

    public S03ClientStatus() {
    }

    @Override
    public int getId() {
        return 0x03;
    }

    /**
     * Write packet data
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        writeVarInt(os, action.actionCode);
    }
}
