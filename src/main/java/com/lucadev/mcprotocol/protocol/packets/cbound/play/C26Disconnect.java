package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.mcprotocol.bots.Bot;
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
public class C26Disconnect extends AbstractPacket implements ReadablePacket {

    private ChatComponent reason;

    @Override
    public int getId() {
        return 0x1A;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
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
}
