package com.lucadev.mcprotocol.util.model;

import java.util.List;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class MOTDResponse {

    private Description description;
    private Players players;
    private Version version;
    private String favicon;

    public Description getDescription() {
        return description;
    }

    public Players getPlayers() {
        return players;
    }

    public Version getVersion() {
        return version;
    }

    public String getFavicon() {
        return favicon;
    }

    @Override
    public String toString() {
        return "MOTDResponse{" +
                "description=" + description +
                ", players=" + players +
                ", version=" + version +
                '}';
    }

    public static class Version {
        private String name;
        private int protocol;

        /**
         * Default constructor for json
         */
        public Version() {

        }

        public String getName() {
            return name;
        }

        public int getProtocol() {
            return protocol;
        }

        @Override
        public String toString() {
            return getName() + " PROTOCOL: " + getProtocol();
        }
    }

    public static class Description {
        private String text;

        /**
         * Default constructor for json
         */
        public Description() {

        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return getText();
        }
    }

    public static class Players {
        private int max;
        private int online;
        private List<Player> sample;

        /**
         * Default constructor for json
         */
        public Players() {

        }

        public int getMax() {
            return max;
        }

        public int getOnline() {
            return online;
        }

        public List<Player> getSample() {
            return sample;
        }

        @Override
        public String toString() {
            return max + "/" + online;
        }
    }

    public static class Player {
        private String name;
        private String id;

        /**
         * Default constructor for json
         */
        public Player() {

        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return getId() + " " + getName();
        }
    }

}
