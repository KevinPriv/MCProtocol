package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;
import com.lucadev.mcprotocol.game.chat.impl.DefaultChatConverterFactory;
import com.lucadev.mcprotocol.protocol.network.client.NetClient;
import com.lucadev.mcprotocol.protocol.network.client.NetClientFactory;
import com.lucadev.mcprotocol.protocol.network.connection.ConnectionFactory;
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
    private final ChatConverterFactory chatConverterFactory = new DefaultChatConverterFactory();

    /**
     * Register a packet which can later be resolved when read from stream.
     * @param state state in which the packet functions(chat message only functions in PLAY for example).
     * @param packet the packet class, id will be resolved from the given packet class
     */
    public void register(State state, Class<? extends Packet> packet) {
        state.registerPacket(packet);
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

    /**
     * Implemented in the abstract class since we do not expect much change. Can always be overriden by a protocol implementation.
     * @return protocol specific NetClient factory implementation.
     */
    @Override
    public NetClientFactory getNetClientFactory() {
        return NetClientFactory.getDefault();
    }

    /**
     * Implemented in the abstract class since we do not expect much change. Can always be override by a protocol implementation.
     * @return protocol specific Connection factory implementation.
     */
    @Override
    public ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getDefault();
    }

}
