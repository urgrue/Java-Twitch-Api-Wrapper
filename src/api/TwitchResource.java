package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TwitchResource {

    public static final String BASE_URL = "https://api.twitch.tv/kraken";
    public static final String API_VERSION = "v3";

    private Map<String, String> headers = new HashMap<>(); // http headers
    private static ObjectMapper objectMapper = new ObjectMapper(); // can reuse, share globally

    public TwitchResource() {
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    private void setHeaders() {
        headers.put("ACCEPT", "application/vnd.twitchtv." + API_VERSION + "+json"); // Specify API version
    }

    protected Map<String, String> getHeaders() {
        return headers;
    }

    private void setClientId(String clientId) {
        headers.put("Client-ID", clientId);
    }

    public <T> T parseResponse(String content, Class<T> type) {
        T obj = null;
        try {
            obj = objectMapper.readValue(content, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
