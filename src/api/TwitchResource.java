package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import http.HttpClient;
import http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TwitchResource {

    private final String baseUrl; // Base url for twitch rest api
    private final String apiVersion; // Api version of the twitch rest api

    private Map<String, String> headers = new HashMap<>(); // http headers
    private static ObjectMapper objectMapper = new ObjectMapper(); // can reuse, share globally

    public TwitchResource(String baseUrl, String apiVersion) {
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    private void setHeaders() {
        headers.put("ACCEPT", "application/vnd.twitchtv." + apiVersion + "+json"); // Specify API version
    }

    protected Map<String, String> getHeaders() {
        return headers;
    }

    private void setClientId(String clientId) {
        headers.put("Client-ID", clientId);
    }

    public HttpResponse getRequest(String url) {
        HttpClient request = new HttpClient();
        HttpResponse httpResponse = null;

        try {
            httpResponse = request.get(url, getHeaders());
        } catch (IOException e) {
            e.printStackTrace(); // Should catch and rethrow connection error to user
        }

        return httpResponse;
    }

    public <T> T parseResponse(String responseContent, Class<T> expectedType) {
        T obj = null;
        try {
            obj = objectMapper.readValue(responseContent, expectedType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected String getApiVersion() {
        return apiVersion;
    }
}
