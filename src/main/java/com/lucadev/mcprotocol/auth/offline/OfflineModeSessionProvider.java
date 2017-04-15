package com.lucadev.mcprotocol.auth.offline;

import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;

import java.io.IOException;

/**
 * SessionProvider that can be used for offline-mode servers.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class OfflineModeSessionProvider extends SessionProvider {

    @Override
    public Session authenticate(String email, String password) throws IOException {
        return new OfflineSession(email);
    }

    /**
     * Authenticate to mojang auth servers.
     *
     * @param session
     * @param hash
     */
    @Override
    public void authenticateServer(Session session, String hash) {

    }
}
