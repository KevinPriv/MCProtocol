package com.lucadev.mcprotocol.protocol.packet.cbound.login;

import com.lucadev.mcprotocol.Bot;
import com.lucadev.mcprotocol.protocol.packet.AbstractPacket;
import com.lucadev.mcprotocol.protocol.packet.ReadablePacket;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lucadev.mcprotocol.protocol.VarHelper.readString;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class C02LoginSuccess extends AbstractPacket implements ReadablePacket {

    private String uuid;
    private String username;

    @Override
    public int getId() {
        return 0x02;
    }

    /**
     * Read the data from the packet in here. This does not include packet id and stuff.
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(Bot bot, DataInputStream is, int totalSize) throws IOException {
        uuid = readString(is, 36);
        username = readString(is, 16);
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "C02LoginSuccess{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
