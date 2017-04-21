package com.lucadev.mcprotocol.auth.yggdrasil;

/**
 * Object representation of the json payload sent to the auth servers to receive a session.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class AuthPayload {

    private Agent agent;
    private String username;
    private String password;

    /**
     * Default constructor added to be sure json can read the payload and convert it to json.
     */
    public AuthPayload() {

    }

    /**
     * Constructor that sets the required payload data.
     *
     * @param agent    the authenticate agent: which service are we authenticating for? In this case Minecraft
     * @param version  protocolVersion number that could be used by auth servers to identify the client protocolVersion(not used as of now)
     * @param username the username credential
     * @param password the password for the given username.
     */
    public AuthPayload(String agent, int version, String username, String password) {
        this.agent = new Agent(agent, version);
        this.username = username;
        this.password = password;
    }

    /**
     * @return agent, which service we are authenticating for. Also contains our client authentication protocolVersion.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * @return the username to authenticate
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password used to authenticate the username with
     * @see #getUsername()
     */
    public String getPassword() {
        return password;
    }

    /**
     * Part of the authentication payload that contains data for which service we are authenticating.
     * We will probably use Minecraft but Mojang also has other games that make use of the same authentication mechanism such as Scrolls.
     */
    public class Agent {
        private String name;
        private int version;

        /**
         * Default constructor for our json library
         */
        public Agent() {
        }

        /**
         * Constructs the agent with the given name and client auth protocolVersion.
         *
         * @param name    identifies which service are we authenticating for? In this case Minecraft but could also be Scrolls.
         * @param version protocolVersion of our auth mechanism(1 as current value but might increase in the future).
         */
        public Agent(String name, int version) {
            this.name = name;
            this.version = version;
        }

        /**
         * @return identifies which service are we authenticating for.
         */
        public String getName() {
            return name;
        }

        /**
         * @return protocolVersion of our auth mechanism.
         */
        public int getVersion() {
            return version;
        }
    }

}
