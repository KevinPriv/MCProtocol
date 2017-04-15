package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;
import com.lucadev.mcprotocol.game.chat.components.ChatComponent;
import com.lucadev.mcprotocol.protocol.packets.Packet;
import com.lucadev.mcprotocol.protocol.packets.PacketListener;
import com.lucadev.mcprotocol.protocol.packets.ReadablePacket;
import com.lucadev.mcprotocol.game.tick.TickEngine;
import com.lucadev.mcprotocol.game.tick.TickListener;

import java.io.IOException;

/**
 * Interface of a protocol which is used for the basic communications between server and client.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface Protocol {

    /**
     * Setup the protocol.
     * @param bot instance of our bot
     */
    void setup(Bot bot);

    /**
     * Get the supported protocol version of the protocol.
     * @return protocol id
     */
    int getVersion();

    /**
     * This method will handle all packets from the handshake state until the play state which means you've logged in.
     * @throws IOException when something goes wrong with the stream or login.
     */
    void serverLogin() throws IOException;

    /**
     * Get a packet from its id and its state.
     * @param id the id of the packet
     * @param state the state that we look the packet in.
     *              Packets can have equal id's but are seperated by the state the connection is in.
     * @return the resolved packet.
     */
    Packet resolvePacket(int id, State state);

    /**
     * @return the state of the connection between server and client.
     * @see State
     */
    State getCurrentState();

    /**
     * Change the state to the given state.
     * @param state new connection state.
     */
    void setCurrentState(State state);

    /**
     * @return get the factory used to create the chat converters.
     */
    ChatConverterFactory getChatConverterFactory();

    /**
     * Packet handle method.
     * @param packet the read packet from stream.
     */
    void handlePacket(ReadablePacket packet);

    /**
     * Register a listener for when a specified packet is read.
     * @param clazz the class of the packet we want to listen for.
     * @param listener the listener to run when we come across the specified packet.
     */
    void registerPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener);

    /**
     * Unregister a listener from a packet.
     * @param clazz the class of the packet we want to unregister.
     * @param listener the listener that was listening to the specified packet.
     */
    void unregisterPacketListener(Class<? extends ReadablePacket> clazz, PacketListener listener);

    /**
     * Handle a tick from the tick engine.
     */
    void tick();

    /**
     * Handle an incoming chat message
     * @param component the chat component that was received.
     * @param position chat components aren't only for the chat box.
     *                 0=chatbox, 1=system message, 2=above hotbar
     */
    void onChatMessage(ChatComponent component, byte position);

    /**
     * Disconnect from the server.
     */
    void disconnect() throws IOException;

    /**
     * @return instance of our tick engine.
     */
    TickEngine getTickEngine();
}
