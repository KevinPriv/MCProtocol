package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.game.chat.ChatConverterFactory;
import com.lucadev.mcprotocol.game.chat.impl.DefaultChatConverterFactory;
import com.lucadev.mcprotocol.protocol.packet.Packet;
import com.lucadev.mcprotocol.protocol.packet.UndefinedPacket;

import java.util.HashMap;

/**
 * Abstract class for protocol which includes methods like varint and such.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class AbstractProtocol implements Protocol {

    /**
     * PACKET ID -> PACKET CLASS
     */
    private HashMap<Integer, Class<? extends Packet>> packetMapStatus = new HashMap<>();
    private HashMap<Integer, Class<? extends Packet>> packetMapLogin = new HashMap<>();
    private HashMap<Integer, Class<? extends Packet>> packetMapPlay = new HashMap<>();

    private State state = State.LOGIN;

    private ChatConverterFactory chatConverterFactory = new DefaultChatConverterFactory();

    public void register(State state, int id, Class<? extends Packet> packet) {
        switch (state) {
            case PLAY:
                if (packetMapPlay.containsKey(id)) {
                    throw new IllegalArgumentException("Packet id already registered");
                }
                packetMapPlay.put(id, packet);

                break;
            case LOGIN:
                if (packetMapLogin.containsKey(id)) {
                    throw new IllegalArgumentException("Packet id already registered");
                }
                packetMapLogin.put(id, packet);

                break;
            case STATUS:
                if (packetMapStatus.containsKey(id)) {
                    throw new IllegalArgumentException("Packet id already registered");
                }
                packetMapStatus.put(id, packet);

                break;
        }
    }

    @Override
    public Packet resolvePacket(int id, State state) {
        try {
            switch (state) {
                case PLAY:
                    if (!packetMapPlay.containsKey(id)) {
                        return new UndefinedPacket(id);
                    }
                    return packetMapPlay.get(id).newInstance();
                case LOGIN:
                    if (!packetMapLogin.containsKey(id)) {
                        return new UndefinedPacket(id);
                    }
                    return packetMapLogin.get(id).newInstance();
                case STATUS:
                    if (!packetMapStatus.containsKey(id)) {
                        return new UndefinedPacket(id);
                    }
                    return packetMapStatus.get(id).newInstance();
            }
        } catch (InstantiationException e) {
            //IS THE CONSTRUCTOR AVAILABLE?
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //IS THE CONSTRUCTOR PUBLIC?
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Current state
     *
     * @return
     */
    @Override
    public State getCurrentState() {
        return state;
    }

    /**
     * Set current state
     *
     * @param state
     */
    @Override
    public void setCurrentState(State state) {
        this.state = state;
    }

    /**
     * Get the factory used to create chat parses.
     *
     * @return
     */
    public ChatConverterFactory getChatConverterFactory() {
        return chatConverterFactory;
    }

}
