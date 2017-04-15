package com.lucadev.mcprotocol.auth;

import com.lucadev.mcprotocol.auth.offline.OfflineModeLoginProvider;
import com.lucadev.mcprotocol.auth.yggdrasil.YggdrasilLoginProvider;

import java.io.IOException;

/**
 * LoginProvider is used to authenticate a user with mojang so he or she can play online.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class LoginProvider {

    public abstract Session login(String email, String password) throws IOException;

    public static LoginProvider getDefaultOnlineProvider() {
        return new YggdrasilLoginProvider();
    }

    /**
     * Provider for offline mode.
     * @return
     */
    public static LoginProvider getDefaultOfflineProvider() {
        return new OfflineModeLoginProvider();
    }


    /**
     * Authenticate to mojang auth servers.
     * @param login
     * @param hash
     */
    public abstract void authenticateServer(Session login, String hash) throws IOException;
}
