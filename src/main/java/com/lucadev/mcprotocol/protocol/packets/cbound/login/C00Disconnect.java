package com.lucadev.mcprotocol.protocol.packets.cbound.login;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.chat.ChatParseException;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C00Disconnect extends AbstractPacket implements ReadablePacket {

    private ChatComponent reason;

    @Override
    public int getId() {
        return 0x00;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        try {
            reason = bot.getProtocol().getChatConverterFactory().createConverter().parse(readString(is));
        } catch (ChatParseException e) {
            e.printStackTrace();
        }
    }

    public ChatComponent getReason() {
        return reason;
    }

    public String getTextReason() {
        if(reason == null) {
            return null;
        }
        return reason.getCompleteText();
    }
}
