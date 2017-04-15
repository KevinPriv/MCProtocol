package com.lucadev.mcprotocol.auth;

import com.lucadev.mcprotocol.auth.offline.OfflineModeSessionProvider;
import com.lucadev.mcprotocol.auth.yggdrasil.YggdrasilSessionProvider;

import java.io.IOException;

/**
 * SessionProvider is used to authenticate a user with mojang so he or she can play online.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class SessionProvider {

    public abstract Session login(String email, String password) throws IOException;

    public static SessionProvider getDefaultOnlineProvider() {
        return new YggdrasilSessionProvider();
    }

    /**
     * Provider for offline mode.
     * @return
     */
    public static SessionProvider getDefaultOfflineProvider() {
        return new OfflineModeSessionProvider();
    }


    /**
     * Authenticate to mojang auth servers.
     * @param login
     * @param hash
     */
    public abstract void authenticateServer(Session login, String hash) throws IOException;
}
