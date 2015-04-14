package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import http.HttpClient;
import http.HttpRequestMethods;
import http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TwitchResource {

    private final String baseUrl; // Base url for twitch rest api
    private final String apiVersion; // Api version of the twitch rest api
    private String clientId; // Client Id of your Twitch App

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

    public void setAuthAccessToken(String accessToken) {
        if (accessToken != null && accessToken.length() > 0) {
            headers.put("Authorization", String.format("OAuth %s", accessToken));
        } else {
            headers.remove("Authorization");
        }
    }

    public void setClientId(String clientId) {
        if (clientId != null && clientId.length() > 0) {
            headers.put("Client-ID", clientId);
        } else {
            headers.remove("Client-ID");
        }
    }

    /**
     * Make a HTTP request to Twitch.tv and genereate a <code>TwitchResponse</code> object.
     * @param requestUrl URL of resource to make request to
     * @param requestMethod HTTP request method. ie: GET, PUT, POST, DELETE
     * @param successCode HTTP status code that signifies a successful response
     * @param expectedType Expected class of containing object result
     * @return <code>TwitchResponse</code> containing request response
     */
    private <T> TwitchResponse<T> request(
            String requestUrl,
            HttpRequestMethods requestMethod,
            Map<String, String> inputData,
            int successCode,
            Class<T> expectedType) {

        // Make Request
        HttpClient httpClient = new HttpClient();
        HttpResponse response = null;

        try {
            response = httpClient.request(requestUrl, requestMethod, getHeaders(), inputData);
        } catch (IOException e) {
            e.printStackTrace(); // Should catch and rethrow connection error to user
        }

        assert response != null;

        // Generate response object
        T obj = null;

        int statusCode = response.getCode();
        TwitchResponse<T> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == successCode) {
            // Success! We have data from Twitch
            obj = parseResponse(response.getContent(), expectedType);
        } else {
            // Error! Parse error message
            ErrorResponse error = parseResponse(response.getContent(), ErrorResponse.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(obj);

        return twitchResponse;
    }

    private <T> T parseResponse(String responseContent, Class<T> expectedType) {
        T obj = null;
        try {
            obj = objectMapper.readValue(responseContent, expectedType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    protected <T> TwitchResponse<T> requestDelete(String requestUrl, int successCode, Class<T> expectedType) {
        return request(requestUrl, HttpRequestMethods.DELETE, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestGet(String requestUrl, int successCode, Class<T> expectedType) {
        return request(requestUrl, HttpRequestMethods.GET, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPut(String requestUrl, int successCode, Class<T> expectedType) {
        return request(requestUrl, HttpRequestMethods.PUT, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPut(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) {
        return request(requestUrl, HttpRequestMethods.PUT, inputData, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPost(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) {
        return request(requestUrl, HttpRequestMethods.POST, inputData, successCode, expectedType);
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected String getApiVersion() {
        return apiVersion;
    }
}
