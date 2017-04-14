package com.lucadev.mcprotocol.auth.yggdrasil;

/**
 * Payload sent to auth servers.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class AuthPayload {

    private Agent agent;
    private String username;
    private String password;

    public AuthPayload() {

    }

    public AuthPayload(String agent, int version, String username, String password) {
        this.agent = new Agent(agent, version);
        this.username = username;
        this.password = password;
    }

    public Agent getAgent() {
        return agent;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public class Agent {
        private String name;
        private int version;

        public Agent() {
        }

        public Agent(String name, int version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public int getVersion() {
            return version;
        }
    }

}
