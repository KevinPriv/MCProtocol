package com.lucadev.mcprotocol.auth.offline;

import com.lucadev.mcprotocol.auth.Session;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class OfflineSession extends Session {

    private static final String OFFLINE_ACCESS_TOKEN = "";
    private static final String OFFLINE_CLIENT_TOKEN = "";
    private static final String OFFLINE_PROFILE_ID = "";


    public OfflineSession(String profileName) {
        super(OFFLINE_ACCESS_TOKEN, OFFLINE_CLIENT_TOKEN, OFFLINE_PROFILE_ID, profileName);
    }

    /**
     * Simple online mode check
     *
     * @return
     */
    @Override
    public boolean isOnline() {
        return false;
    }
}
