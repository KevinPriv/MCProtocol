package com.lucadev.mcprotocol.protocol.packets.cbound.play;

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
public class C16ChatMessage extends AbstractPacket implements ReadablePacket {

    private ChatComponent chatComponent;
    private byte position;
    private String rawJson;

    @Override
    public int getId() {
        return 0x0F;
    }

    /**
     * Read the data from the packets in here. This does not include packets id and stuff.
     *
     * @param is
     * @param totalSize total size of the data we're able to read.
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        rawJson = readString(is);
        try {
            chatComponent = bot.getProtocol().getChatConverterFactory().createConverter().parse(rawJson);
        } catch (ChatParseException e) {
            e.printStackTrace();
        }
        position = is.readByte();
    }

    public ChatComponent getChatComponent() {
        return chatComponent;
    }

    public byte getPosition() {
        return position;
    }

    public String getRawJson() {
        return rawJson;
    }
}
