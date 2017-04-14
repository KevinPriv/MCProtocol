package com.lucadev.mcprotocol.responses;

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

    public class Version {
        private String name;
        private int protocol;

        public String getName() {
            return name;
        }

        public int getProtocol() {
            return protocol;
        }
    }

    public class Description {
        private String text;

        public String getText() {
            return text;
        }
    }

    public class Players {
        private int max;
        private int online;
        private List<Player> sample;

        public int getMax() {
            return max;
        }

        public int getOnline() {
            return online;
        }

        public List<Player> getSample() {
            return sample;
        }
    }

    public class Player {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

}
