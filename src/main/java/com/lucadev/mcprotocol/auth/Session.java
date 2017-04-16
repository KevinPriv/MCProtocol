package com.lucadev.mcprotocol.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Abstract user session used for authentication.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class Session {

    private String accessToken;
    private String clientToken;

    private String profileId;
    private String profileName;

    /**
     * Constructor that sets up the session information.
     * @param accessToken random hex string used to authenticate the user without password(just like a web cookie)
     * @param clientToken random generated UUID(need to read more documentation about this)
     * @param profileId player uuid
     * @param profileName player username
     */
    public Session(String accessToken, String clientToken, String profileId, String profileName) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.profileId = profileId;
        this.profileName = profileName;
    }

    /**
     * @return random hex string used to authenticate the user without password(just like a web cookie)
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return random generated UUID(need to read more documentation about this)
     */
    public String getClientToken() {
        return clientToken;
    }

    /**
     * @return uuid of the player that was authenticated
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * @return username of the player that was authenticated
     */
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
     * Check if the session is able to join online-mode servers.
     *
     * @return the state of being able to join online-mode servers
     */
    @JsonIgnore
    public abstract boolean isOnline();
}
