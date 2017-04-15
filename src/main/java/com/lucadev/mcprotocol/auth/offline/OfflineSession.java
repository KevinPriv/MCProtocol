package com.lucadev.mcprotocol.auth.offline;

import com.lucadev.mcprotocol.auth.Session;

/**
 * A session implementation that is used when the offline session provider is selected.
 * This session can only be used on offline-mode servers.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class OfflineSession extends Session {

    /**
     * Access token to use for the OfflineSession
     */
    private static final String OFFLINE_ACCESS_TOKEN = "";

    /**
     * Client token to use for the OfflineSession
     */
    private static final String OFFLINE_CLIENT_TOKEN = "";

    /**
     * Profile ID to use for the OfflineSession
     */
    private static final String OFFLINE_PROFILE_ID = "";


    /**
     * Constructor that sets up session with the specified profile name.
     * @param profileName the name of the player
     */
    public OfflineSession(String profileName) {
        super(OFFLINE_ACCESS_TOKEN, OFFLINE_CLIENT_TOKEN, OFFLINE_PROFILE_ID, profileName);
    }

    /**
     * Check if the session is able to join online-mode servers.
     * Simply false for us since this is for offline-mode servers only.
     *
     * @return the state of being able to join online-mode servers
     */
    @Override
    public boolean isOnline() {
        return false;
    }
}
