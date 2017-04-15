package com.lucadev.mcprotocol.auth.yggdrasil;

import com.lucadev.mcprotocol.auth.Session;

import static com.lucadev.mcprotocol.util.StringUtil.isNotNullOrEmpty;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class YggdrasilSession extends Session {

    public YggdrasilSession(String accessToken, String clientToken, String profileId, String profileName) {
        super(accessToken, clientToken, profileId, profileName);
    }

    /**
     * Simple online mode check
     *
     * @return
     */
    @Override
    public boolean isOnline() {
        return isNotNullOrEmpty(getAccessToken()) &&
                isNotNullOrEmpty(getClientToken()) &&
                isNotNullOrEmpty(getProfileId());
    }
}
