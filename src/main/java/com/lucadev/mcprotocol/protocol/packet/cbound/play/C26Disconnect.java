package com.lucadev.mcprotocol.protocol.packet.cbound.play;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.chat.ChatParseException;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

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
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param bot
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        try {
            reason = bot.getProtocol().getChatConverterFactory().createParser().parse(readString(is));
        } catch (ChatParseException e) {
            e.printStackTrace();
        }
    }

    public ChatComponent getReason() {
        return reason;
    }
}
