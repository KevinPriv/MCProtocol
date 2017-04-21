package com.lucadev.mcprotocol.auth.yggdrasil;

import com.lucadev.mcprotocol.auth.yggdrasil.cache.FileYggdrasilSessionStorage;
import com.lucadev.mcprotocol.auth.yggdrasil.cache.YggdrasilSessionStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Extension of the existing Yggdrasil session provider.
 * This simply stores and loads the session with accesstoken using a YggdrasilSessionStorage implementation
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class CacheEnabledYggdrasilSessionProvider extends YggdrasilSessionProvider {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CacheEnabledYggdrasilSessionProvider.class);


    private YggdrasilSessionStorage storageMethod;

    /**
     * Constructor that will use file storage for storing a session.
     */
    public CacheEnabledYggdrasilSessionProvider() {
        this(new FileYggdrasilSessionStorage());
    }

    /**
     * Constructor that sets the storage method for a session.
     *
     * @param storageMethod method of storing the session.
     */
    public CacheEnabledYggdrasilSessionProvider(YggdrasilSessionStorage storageMethod) {
        this.storageMethod = storageMethod;
        logger.info("Setup session caching with " + storageMethod.getClass().getName());
    }

    /**
     * Authenticate with the given credentials and obtain a Session object.
     *
     * @param email    account email or username(depends on migrated account, online/offline-mode etc...)
     * @param password the password used to authenticate the given email/username
     * @return the session obtained from trying to authenticating with the given credentials.
     * @throws IOException will be thrown when something unexpected happens during authentication.
     *                     An example would be that the auth servers could be down or you lost your internet connection.
     */
    @Override
    public YggdrasilSession authenticate(String email, String password) throws IOException {
        if (storageMethod == null) {
            throw new IOException("No session storage method set!");
        }
        YggdrasilSession cachedSession = storageMethod.load();
        if (cachedSession != null) {
            if (validate(cachedSession)) {
                logger.info("Cached session is still valid.");
                return cachedSession;
            } else {
                logger.info("Cached session no longer valid. Re-authenticating.");
            }
        }
        YggdrasilSession session = super.authenticate(email, password);
        storageMethod.store(session);
        return session;
    }

    /**
     * @return session caching/storage method
     */
    public YggdrasilSessionStorage getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(YggdrasilSessionStorage storageMethod) {
        this.storageMethod = storageMethod;
    }
}
