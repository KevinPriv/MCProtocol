package com.lucadev.mcprotocol.auth.yggdrasil;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Implementation for the Yggdrasil auth mechanism from Mojang.
 * This is the current method of authenticating users in Minecraft.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @see com.lucadev.mcprotocol.auth.SessionProvider
 */
public class YggdrasilSessionProvider extends SessionProvider<YggdrasilSession> {

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

    private final ObjectMapper objectMapper;

    public YggdrasilSessionProvider() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Authenticate with the given credentials and obtain a Session object.
     * @param email account email or username(depends on migrated account, online/offline-mode etc...)
     * @param password the password used to authenticate the given email/username
     * @return the session obtained from trying to authenticating with the given credentials.
     * @throws IOException will be thrown when something unexpected happens during authentication.
     *                      An example would be that the auth servers could be down or you lost your internet connection.
     */
    @Override
    public YggdrasilSession authenticate(String email, String password) throws IOException {
        logger.info("Authenticating user through yggdrasil");
        AuthPayload authPayload = new AuthPayload(AGENT, AGENT_VERSION, email, password);
        HttpsURLConnection conn = postJson("/authenticate", authPayload);
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String response = getStringResponse(conn);
            JsonNode node = objectMapper.readTree(response);
            String accessToken = node.get("accessToken").textValue();
            String clientToken = node.get("clientToken").textValue();
            JsonNode profile = node.get("selectedProfile");
            String profileId = profile.get("id").textValue();
            String profileName = profile.get("name").textValue();
            return new YggdrasilSession(accessToken, clientToken, profileId, profileName);
        } else {
            //TODO: Handle error
            throw new YggdrasilAuthException("No status code 200 returned by auth servers!");
        }
    }

    /**
     * Validate if the session is still usable for authentication.
     *
     * @param session the session to check against.
     * @return true if we can use it, false if not.
     * @throws IOException when we could not check it.
     */
    @Override
    public boolean validate(YggdrasilSession session) throws IOException {
        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("accessToken", session.getAccessToken());
        payload.put("clientToken", session.getClientToken());
        HttpsURLConnection resp = postJson("/validate", objectMapper.writeValueAsString(payload));
        int code = resp.getResponseCode();
        //code 204 means it's valid
        if(code == HttpsURLConnection.HTTP_NO_CONTENT) {
            return true;
        }

        //else an error is returned
        AuthError error = objectMapper.readValue(getStringResponse(resp), AuthError.class);
        logger.info(new YggdrasilAuthException(error).getMessage());
        return false;
    }

    /**
     * Posts json data to the given endpoint on the authserver
     * @param endpoint endpoint to connect to such as /authenticate
     * @param obj object to convert to json
     * @return connection that was used for the request
     * @throws IOException when something goes wrong in the request.
     */
    private HttpsURLConnection postJson(String endpoint, Object obj) throws IOException {
        return postJson(endpoint, objectMapper.writeValueAsString(obj));
    }

    /**
     * Posts json data to the given endpoint on the authserver
     * @param endpoint endpoint to connect to such as /authenticate
     * @param encodedPayload json string
     * @return connection that was used for the request
     * @throws IOException when something goes wrong in the request.
     */
    protected HttpsURLConnection postJson(String endpoint, String encodedPayload) throws IOException {
        if(!endpoint.startsWith("/")) {
            endpoint = "/" + endpoint;
        }
        URL url = new URL(BASE_URL + endpoint);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("Content-Length", String.valueOf(encodedPayload.length()));
        OutputStream os = conn.getOutputStream();
        os.write(encodedPayload.getBytes());
        return conn;
    }

    /**
     * Read the string response from a request
     * @param conn the connection with which the request was made.
     * @return data returned by the request as a string.
     * @throws IOException when we cannot convert it somehow.
     */
    protected String getStringResponse(HttpsURLConnection conn) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), CHARSET));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    /**
     * Parses the error json string to an AuthError
     * @param error json string
     * @return object containing error information
     * @throws IOException when we could not read the json correctly.
     */
    protected AuthError parseError(String error) throws IOException {
        return objectMapper.readValue(error, AuthError.class);
    }

    /**
     * Authenticate to a service so that we are able to join servers. This is mostly used for online-mode
     *
     * @param session the user session
     * @param hash the hash generated from earlier steps. This hash is used to verify the user.
     */
    @Override
    public void authenticateServer(YggdrasilSession session, String hash) throws IOException {
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
