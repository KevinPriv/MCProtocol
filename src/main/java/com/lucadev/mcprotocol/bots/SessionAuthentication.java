package com.lucadev.mcprotocol.bots;

import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;

import java.io.IOException;

/**
 * Interface that may be implemented by certain bots to make use of sessions/user authentication.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public interface SessionAuthentication {

    /**
     * @return session that the implementor is currently authenticated with.
     */
    Session getSession();

    /**
     * @return provider implementation used to authenticate with.
     */
    SessionProvider getSessionProvider();

    /**
     * Authenticate and replace the current session.
     *
     * @param email    email or username
     * @param password password to auth with
     * @throws IOException if authentication fails somehow.
     */
    void authenticate(String email, String password) throws IOException;
}
