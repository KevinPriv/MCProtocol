package com.lucadev.mcprotocol.auth.yggdrasil.cache;

import com.lucadev.mcprotocol.auth.yggdrasil.YggdrasilSession;

import java.io.IOException;

/**
 * Interface that offers methods on loading and storing a Yggdrasil session.
 * This is mainly used for the CacheEnabledYggdrasilSessionProvider
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.auth.yggdrasil.CacheEnabledYggdrasilSessionProvider
 */
public interface YggdrasilSessionStorage {

    /**
     * Stores the given session
     * @param session the YggdrasilSession to store.
     * @throws IOException thrown when we somehow cannot store it.
     */
    void store(YggdrasilSession session) throws IOException;

    /**
     * Loads the YggdrasilSession
     * @return loaded YggdrasilSession that has not been verified yet.
     * @throws IOException thrown when we could not load it.
     */
    YggdrasilSession load() throws IOException;
}
