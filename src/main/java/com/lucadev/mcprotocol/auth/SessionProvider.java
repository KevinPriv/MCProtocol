package com.lucadev.mcprotocol.auth;

import com.lucadev.mcprotocol.auth.offline.OfflineModeSessionProvider;
import com.lucadev.mcprotocol.auth.yggdrasil.YggdrasilSessionProvider;

import java.io.IOException;

/**
 * SessionProvider is a class that provides the ability to obtain an user session for the game through authentication.
 * This authentication can happen in different ways(online and offline-mode).
 * <p>
 * The session provider not only provides the ability to authenticate a Minecraft account it also has the ability to authenticate to a server.
 * This is required before joining a server as the server implementation checks if the client is allowed to join.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class SessionProvider<T extends Session> {

    /**
     * Authenticate with the given credentials and obtain a Session object.
     *
     * @param email    account email or username(depends on migrated account, online/offline-mode etc...)
     * @param password the password used to authenticate the given email/username
     * @return the session obtained from trying to authenticating with the given credentials.
     * @throws IOException will be thrown when something unexpected happens during authentication.
     *                     An example would be that the auth servers could be down or you lost your internet connection.
     */
    public abstract T authenticate(String email, String password) throws IOException;

    /**
     * Validate if the session is still usable for authentication.
     *
     * @param session the session to check against.
     * @return true if we can use it, false if not.
     * @throws IOException when we could not check it.
     */
    public abstract boolean validate(T session) throws IOException;

    /**
     * @return obtain a new instance of the default online-mode session provider that supports the current game protocolVersion.
     */
    public static SessionProvider getDefaultOnlineProvider() {
        return new YggdrasilSessionProvider();
    }

    /**
     * @return obtain a new instance of the default online-mode session provider that supports the current game protocolVersion.
     */
    public static SessionProvider getDefaultOfflineProvider() {
        return new OfflineModeSessionProvider();
    }


    /**
     * Authenticate to a service so that we are able to join servers. This is mostly used for online-mode
     *
     * @param session the user session
     * @param hash    the hash generated from earlier steps. This hash is used to verify the user.
     */
    public abstract void authenticateServer(T session, String hash) throws IOException;
}
