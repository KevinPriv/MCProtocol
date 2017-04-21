package com.lucadev.mcprotocol.protocol.packets.sbound.play.client;

import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.WritablePacket;

import java.io.IOException;

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
     * Write packets data
     *
     * @param buff
     * @throws IOException
     */
    @Override
    public void write(VarDataBuffer buff) throws IOException {
        buff.writeVarInt(action.actionCode);
    }
}
