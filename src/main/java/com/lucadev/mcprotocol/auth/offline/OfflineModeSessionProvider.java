package com.lucadev.mcprotocol.auth.offline;

import com.lucadev.mcprotocol.auth.SessionProvider;

import java.io.IOException;

/**
 * SessionProvider that can be used for offline-mode servers.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class OfflineModeSessionProvider extends SessionProvider<OfflineSession> {

    /**
     * Ignores legit authentication and simply returns a session with the given username that can only be used for Offline mode
     *
     * @param email    for offline-mode this requires to be your player name and not an e-mail address.
     * @param password skipped completely since we are not actually authenticating.
     * @return a session that contains empty data except the given username.
     * @throws IOException will be thrown when something unexpected happens during authentication.
     *                     An example would be that the auth servers could be down or you lost your internet connection.
     */
    @Override
    public OfflineSession authenticate(String email, String password) throws IOException {
        return new OfflineSession(email);
    }

    /**
     * Offline mode always returns true here.
     *
     * @param session the session to check against.
     * @return true if we can use it, false if not.
     * @throws IOException when we could not check it.
     */
    @Override
    public boolean validate(OfflineSession session) throws IOException {
        return true;
    }

    /**
     * Authenticate to a service so that we are able to join servers.
     * This can be completely skipped by offline-mode servers because we do not have the required session data to authenticte.
     * Offline-mode servers simply skip this step.
     *
     * @param session the user session
     * @param hash    the hash generated from earlier steps. This hash is used to verify the user.
     */
    @Override
    public void authenticateServer(OfflineSession session, String hash) {

    }
}
