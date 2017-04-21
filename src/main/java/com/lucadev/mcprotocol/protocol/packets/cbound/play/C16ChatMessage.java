package com.lucadev.mcprotocol.protocol.packets.cbound.play;

import com.lucadev.mcprotocol.bots.Bot;
import com.lucadev.mcprotocol.game.chat.ChatParseException;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.network.io.VarDataBuffer;
import com.lucadev.mcprotocol.protocol.packets.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;

import java.io.IOException;

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
     * @param buff
     * @throws IOException
     */
    @Override
    public void read(Bot bot, VarDataBuffer buff) throws IOException {
        rawJson = buff.readVarString();
        try {
            chatComponent = bot.getProtocol().getChatConverterFactory().createConverter().parse(rawJson);
        } catch (ChatParseException e) {
            e.printStackTrace();
        }
        position = buff.readByte();
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
