package com.lucadev.mcprotocol.auth.yggdrasil.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucadev.mcprotocol.auth.yggdrasil.YggdrasilSession;
import com.lucadev.mcprotocol.util.StringUtil;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the YggdrasilSessionStorage using a file to store it.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class FileYggdrasilSessionStorage implements YggdrasilSessionStorage {

    /**
     * Default filename to use if no file was specified in the constructors.
     */
    private static final String DEFAULT_FILENAME = "session.json";

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final File file;

    /**
     * Default constructor uses a file named "session.json" in the current working directory.
     */
    public FileYggdrasilSessionStorage() {
        this("session.json");
    }

    /**
     * @param file specify the file we will use.
     */
    public FileYggdrasilSessionStorage(String file) {
        this(new File(StringUtil.isNullOrEmpty(file) ? DEFAULT_FILENAME : file));
    }

    /**
     * @param file the file to store and load from.
     */
    public FileYggdrasilSessionStorage(File file) {
        this.file = file;
    }

    /**
     * Stores the given session
     *
     * @param session the YggdrasilSession to store.
     * @throws IOException thrown when we somehow cannot store it.
     */
    @Override
    public void store(YggdrasilSession session) throws IOException {
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("Could not delete previous session file.");
            }
        }

        if (!file.createNewFile()) {
            throw new IOException("Could not create session file.");
        }

        jsonMapper.writeValue(file, session);
    }

    /**
     * Loads the YggdrasilSession
     *
     * @return loaded YggdrasilSession that has not been verified yet.
     * @throws IOException thrown when we could not load it.
     */
    @Override
    public YggdrasilSession load() throws IOException {
        if (!file.exists()) {
            return null;
        }

        return jsonMapper.readValue(file, YggdrasilSession.class);
    }
}
