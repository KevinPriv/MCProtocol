package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;
import com.lucadev.mcprotocol.game.chat.impl.DefaultChatConverterFactory;
import com.lucadev.mcprotocol.protocol.packets.Packet;
import com.lucadev.mcprotocol.protocol.packets.UndefinedPacket;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Abstract implementation of protocol which contains packet register&resolve, chat converters and state information.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see Protocol
 */
public abstract class AbstractProtocol implements Protocol {

    private State state = State.HANDSHAKE;
    private ChatConverterFactory chatConverterFactory = new DefaultChatConverterFactory();

    /**
     * Register a packet which can later be resolved when read from stream.
     * @param state state in which the packet functions(chat message only functions in PLAY for example).
     * @param id the packet id
     * @param packet the packet class
     */
    public void register(State state, int id, Class<? extends Packet> packet) {
        state.registerPacket(id, packet);
    }

    /**
     * Get a packet from its id and its state.
     * @param id the id of the packet
     * @param state the state that we look the packet in.
     *              Packets can have equal id's but are seperated by the state the connection is in.
     * @return the resolved packet.
     */
    @Override
    public Packet resolvePacket(int id, State state) {
        return state.resolvePacket(id);
    }

    /**
     * @return the state of the connection between server and client.
     * @see State
     */
    @Override
    public State getCurrentState() {
        return state;
    }

    /**
     * Change the state to the given state.
     * @param state new connection state.
     */
    @Override
    public void setCurrentState(State state) {
        this.state = state;
    }

    /**
     * @return get the factory used to create the chat converters.
     */
    public ChatConverterFactory getChatConverterFactory() {
        return chatConverterFactory;
    }

}
