package com.lucadev.mcprotocol.auth.offline;

import com.lucadev.mcprotocol.auth.SessionProvider;
import com.lucadev.mcprotocol.auth.Session;

import java.io.IOException;

/**
 * Login provider for offline mode
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class OfflineModeSessionProvider extends SessionProvider {

    @Override
    public Session login(String email, String password) throws IOException {
        return new OfflineSession(email);
    }

    /**
     * Authenticate to mojang auth servers.
     *
     * @param login
     * @param hash
     */
    @Override
    public void authenticateServer(Session login, String hash) {

    }
}
