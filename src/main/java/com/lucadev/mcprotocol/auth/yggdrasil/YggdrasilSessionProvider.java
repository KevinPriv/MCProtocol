package com.lucadev.mcprotocol.auth.yggdrasil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lucadev.mcprotocol.auth.AuthException;
import com.lucadev.mcprotocol.auth.Session;
import com.lucadev.mcprotocol.auth.SessionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * Implementation for the Yggdrasil auth mechanism from Mojang.
 * This is the current method of authenticating users in Minecraft.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.auth.SessionProvider
 */
public class YggdrasilSessionProvider extends SessionProvider {

    /**
     * System logger
     */
    private static final Logger logger = LoggerFactory.getLogger(YggdrasilSessionProvider.class);

    /**
     * Base url of the Yggdrasil auth server
     */
    private static final String BASE_URL = "https://authserver.mojang.com";

    /**
     * Agent name that will be used in the authentication payload.
     */
    private static final String AGENT = "Minecraft";

    /**
     * Authentication mechanism protocolVersion that will be used in the authentication payload.
     */
    private static final int AGENT_VERSION = 1;

    /**
     * Server auth url used for verifying server joins. Has nothing to do with logging an user in.
     */
    private static final String SERVER_AUTH_URL = "https://sessionserver.mojang.com/session/minecraft/join";

    /**
     * Content type that will be sent in the request to the auth servers. In this case it's the json mime type.
     */
    private static final String CONTENT_TYPE = "application/json";

    /**
     * Charset to encode and decode with. In this case the widely used UTF-8
     */
    private static final String CHARSET = "UTF-8";

    /**
     * Authenticate with the given credentials and obtain a Session object.
     * @param email account email or username(depends on migrated account, online/offline-mode etc...)
     * @param password the password used to authenticate the given email/username
     * @return the session obtained from trying to authenticating with the given credentials.
     * @throws IOException will be thrown when something unexpected happens during authentication.
     *                      An example would be that the auth servers could be down or you lost your internet connection.
     */
    @Override
    public Session authenticate(String email, String password) throws IOException {
        logger.info("Authenticating user through yggdrasil");
        AuthPayload authPayload = new AuthPayload(AGENT, AGENT_VERSION, email, password);
        URL url = new URL(BASE_URL + "/authenticate");
        ObjectMapper objectMapper = new ObjectMapper();
        String encodedPayload = objectMapper.writeValueAsString(authPayload);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("Content-Length", String.valueOf(encodedPayload.length()));
        OutputStream os = conn.getOutputStream();
        os.write(encodedPayload.getBytes());

        StringBuilder sb = new StringBuilder();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), CHARSET));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            String response = sb.toString();
            JsonNode node = objectMapper.readTree(response);
            String accessToken = node.get("accessToken").textValue();
            String clientToken = node.get("clientToken").textValue();
            JsonNode profile = node.get("selectedProfile");
            String profileId = profile.get("id").textValue();
            String profileName = profile.get("name").textValue();
            return new YggdrasilSession(accessToken, clientToken, profileId, profileName);
        } else {
            //TODO: Handle error
            logger.info(conn.getResponseMessage());
        }
        return null;
    }

    /**
     * Authenticate to a service so that we are able to join servers. This is mostly used for online-mode
     *
     * @param session the user session
     * @param hash the hash generated from earlier steps. This hash is used to verify the user.
     */
    @Override
    public void authenticateServer(Session session, String hash) throws IOException {
        logger.info("Authenticating to mojang session servers.");
        URL url = new URL(SERVER_AUTH_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        //TODO MAKE CORRECT JSON OBJECT
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("accessToken", session.getAccessToken());
        objectNode.put("selectedProfile", session.getProfileId().replace("-", ""));
        objectNode.put("serverId", hash);

        String encodedPayload = objectMapper.writeValueAsString(objectNode);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("Content-Length", String.valueOf(encodedPayload.length()));
        OutputStream os = conn.getOutputStream();
        os.write(encodedPayload.getBytes());

        StringBuilder sb = new StringBuilder();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) {
            logger.info("Successful auth to mojang session servers!");
        } else {
            //TODO: Handle error
            logger.info(conn.getResponseMessage());
            throw new AuthException("Did not receive 204 NO CONTENT");
        }
    }
}
