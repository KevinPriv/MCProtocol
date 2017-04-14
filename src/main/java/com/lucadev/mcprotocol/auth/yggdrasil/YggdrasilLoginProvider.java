package com.lucadev.mcprotocol.auth.yggdrasil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lucadev.mcprotocol.auth.AuthException;
import com.lucadev.mcprotocol.auth.LoginProvider;
import com.lucadev.mcprotocol.auth.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * Implementation for the Yggdrasil auth mechanism from mojang.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class YggdrasilLoginProvider extends LoginProvider {

    private static final Logger logger = LoggerFactory.getLogger(YggdrasilLoginProvider.class);

    /**
     * Base url for auth
     */
    private static final String BASE_URL = "https://authserver.mojang.com";
    private static final String SERVER_AUTH_URL = "https://sessionserver.mojang.com/session/minecraft/join";
    private static final String CONTENT_TYPE = "application/json";
    private static final String AGENT = "Minecraft";
    private static final int AGENT_VERSION = 1;
    private static final String CHARSET =  "UTF-8";

    @Override
    public Session login(String email, String password) throws IOException {
        logger.info("Authenticating user through yggdrasil");
        AuthPayload authPayload = new AuthPayload(AGENT, AGENT_VERSION, email, password);
        URL url = new URL(BASE_URL + "/authenticate");
        ObjectMapper objectMapper = new ObjectMapper();
        String encodedPayload = objectMapper.writeValueAsString(authPayload);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", CONTENT_TYPE);
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedPayload.length()));
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
            return new Session(accessToken, clientToken, profileId, profileName);
        } else {
            //TODO: Handle error
            logger.info(conn.getResponseMessage());
        }
        return null;
    }

    /**
     * Authenticate to mojang auth servers.
     *
     * @param login
     * @param hash
     */
    @Override
    public void authenticateServer(Session login, String hash) throws IOException {
        logger.info("Authenticating to mojang session servers.");
        URL url = new URL(SERVER_AUTH_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        //TODO MAKE CORRECT JSON OBJECT
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("accessToken", login.getAccessToken());
        objectNode.put("selectedProfile", login.getProfileId().replace("-", ""));
        objectNode.put("serverId", hash);

        String encodedPayload = objectMapper.writeValueAsString(objectNode);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty( "Content-Type", CONTENT_TYPE);
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedPayload.length()));
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
