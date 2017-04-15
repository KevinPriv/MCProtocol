package com.lucadev.mcprotocol.auth;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class Session {

    private String accessToken;
    private String clientToken;

    private String profileId;
    private String profileName;

    public Session(String accessToken, String clientToken, String profileId, String profileName) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.profileId = profileId;
        this.profileName = profileName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "accessToken='" + accessToken + '\'' +
                ", clientToken='" + clientToken + '\'' +
                ", profileId='" + profileId + '\'' +
                ", profileName='" + profileName + '\'' +
                '}';
    }

    /**
     * Simple online mode check
     * @return
     */
    public abstract boolean isOnline();
}
