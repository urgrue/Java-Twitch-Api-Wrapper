package main.java.com.mb3364.twitch.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * AbstractResource is the abstract base class of a Twitch resource.
 * A resource provides the functionality to access the REST endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public abstract class AbstractResource {

    protected static final ObjectMapper objectMapper = new ObjectMapper(); // can reuse, share globally
    private final String baseUrl; // Base url for twitch rest api
    protected Map<String, String> headers = new HashMap<String, String>(); // http headers

    /**
     * Construct a resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    protected AbstractResource(String baseUrl, int apiVersion) {
        this.baseUrl = baseUrl;
        headers.put("ACCEPT", "application/vnd.twitchtv.v" + Integer.toString(apiVersion) + "+json"); // Specify API version
        configureObjectMapper();
    }

    /**
     * Configure the Jackson JSON {@link ObjectMapper} to properly parse the API responses.
     */
    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    /**
     * Sets the authentication access token to be included in the HTTP headers of each
     * API request.
     *
     * @param accessToken the user's authentication access token
     */
    public void setAuthAccessToken(String accessToken) {
        if (accessToken != null && accessToken.length() > 0) {
            headers.put("Authorization", String.format("OAuth %s", accessToken));
        } else {
            headers.remove("Authorization");
        }
    }

    /**
     * Sets the application's client ID to be included in the HTTP headers of each API request.
     *
     * @param clientId the application's client ID
     */
    public void setClientId(String clientId) {
        if (clientId != null && clientId.length() > 0) {
            headers.put("Client-ID", clientId);
        } else {
            headers.remove("Client-ID");
        }
    }

    /**
     * Get the base URL to the Twitch API. Intended to be called by subclasses when generating
     * their resource URL endpoint.
     *
     * @return the base URL to the Twitch API
     */
    protected String getBaseUrl() {
        return baseUrl;
    }
}
