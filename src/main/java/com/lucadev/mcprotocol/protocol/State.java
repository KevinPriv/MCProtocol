package com.lucadev.mcprotocol.protocol;

import com.lucadev.mcprotocol.protocol.packets.Packet;
import com.lucadev.mcprotocol.protocol.packets.UndefinedPacket;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Enum that is used to identify the connection state.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public enum State {

    /**
     * Handshake state is only used for the first packet sent most of the time.
     */
    HANDSHAKE(0),

    /**
     * Status state is used when fetching server status.
     * An example of this would be fetching MOTD.
     */
    STATUS(1),

    /**
     * Login state is used to handle the login sequence between client and server.
     */
    LOGIN(2),

    /**
     * Play state is the state in which the most packets will be sent such as motion updates, block data etc...
     */
    PLAY(3);

    /**
     * Integer representation of the state. Used when sending or receiving a handshake.
     */
    private int stateCode;

    /**
     * Packets associated with the state
     */
    private HashMap<Integer, Class<? extends Packet>> packets;

    /**
     * Constructor for the state which sets the integer representation of the state.
     * @param stateCode the integer value linked to the state
     */
    State(int stateCode) {
        this.stateCode = stateCode;
        this.packets = new HashMap<>();
    }

    /**
     * @return integer value of the state.
     */
    public int getStateCode() {
        return stateCode;
    }

    /**
     * Register a packet to the state.
     * @param id packet id
     * @param clazz packet class
     */
    public void registerPacket(int id, Class<? extends Packet> clazz) {
        try {
            verifyPacketClass(clazz);
        } catch (ProtocolException e) {
            e.printStackTrace();
            return;
        }
        if(packets.containsKey(id)) {
            throw new IllegalStateException("Packet id 0x" +
                    Integer.toHexString(id).toUpperCase() + " already registered for state " + name());
        }

        packets.put(id, clazz);
    }

    /**
     * Verifies if the class meets requirements such as a default constructor and whatnot.
     * @param packet the packet class to inspect
     * @throws ProtocolException gets thrown when the packet does not meet requirement which result into a protocol exception.
     */
    private void verifyPacketClass(Class<? extends Packet> packet) throws ProtocolException {
        try {
            //gets the declared constructor with no parameters. Declared means it might not be publicly accessable.
            Constructor con = packet.getDeclaredConstructor();
            //Checks if we can access it.
            if(!Modifier.isPublic(con.getModifiers())) {
                throw new ProtocolException("Packet " + packet.getName() + " has a non public default constructor.");
            }

            //Well it's for the rest.
        } catch (NoSuchMethodException e) {
            throw new ProtocolException("No default constructor discovered in packet " + packet.getName(), e);
        }
    }

    /**
     * Resolve a packet id to an instance of the packet from this state.
     * @param id the packet id to resolve.
     * @return resolved packet object or an UndefinedPacket object if the packet id was not registered.
     * @see com.lucadev.mcprotocol.protocol.packets.UndefinedPacket
     */
    public Packet resolvePacket(int id) {
        if(!packets.containsKey(id)) {
            return new UndefinedPacket(id);
        }
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new UndefinedPacket(id);
    }
}
