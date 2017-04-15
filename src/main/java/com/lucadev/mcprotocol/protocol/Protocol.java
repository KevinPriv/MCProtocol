package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.packet.Packet;
import com.lucadev.mcprotocol.protocol.packet.PacketListener;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;
import com.lucadev.mcprotocol.tick.TickEngine;
import com.lucadev.mcprotocol.tick.TickListener;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Protocol {

    /**
     * Setup everything in here to prepare a connection tot he server
     * @param bot
     */
    void setup(Bot bot);

    /**
     * Get the protocol version
     * @return
     */
    int getVersion();

    /**
     * Handshake step to join the server.
     */
    void serverLogin() throws IOException;

    /**
     * Find a packet
     * @param id
     * @param state
     * @return
     */
    Packet resolvePacket(int id, State state);

    /**
     * Current state
     * @return
     */
    State getCurrentState();

    /**
     * Set current state
     * @param state
     */
    void setCurrentState(State state);

    /**
     * Get the factory used to create chat parses.
     * @return
     */
    ChatConverterFactory getChatConverterFactory();

    void handlePacket(ReadablePacket packet);

    void registerPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener);
    void unregisterPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener);

    void tick();

    /**
     * Chat message
     * @param component the chat component
     * @param position 0=chatbox, 1=system message, 2=above hotbar
     */
    void onChatMessage(ChatComponent component, byte position);

    void register(int tickDelay, TickListener listener);

    void disconnect();

    TickEngine getTickEngine();
}
