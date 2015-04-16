package api.resources;

import api.TwitchResponse;
import api.models.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import http.HttpClient;
import http.HttpRequestMethods;
import http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResource {

    private static ObjectMapper objectMapper = new ObjectMapper(); // can reuse, share globally
    private final String baseUrl; // Base url for twitch rest api
    private Map<String, String> headers = new HashMap<>(); // http headers

    public AbstractResource(String baseUrl, String apiVersion) {
        this.baseUrl = baseUrl;
        headers.put("ACCEPT", "application/vnd.twitchtv." + apiVersion + "+json"); // Specify API version
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
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
     * Make a HTTP request to Twitch.tv and generate a <code>TwitchResponse</code> object.
     *
     * @param requestUrl    URL of resource to make request to
     * @param requestMethod HTTP request method. ie: GET, PUT, POST, DELETE
     * @param successCode   HTTP status code that signifies a successful response
     * @param expectedType  Expected class of containing object result
     * @return <code>TwitchResponse</code> containing request response
     */
    private <T> TwitchResponse<T> request(String requestUrl,
                                          HttpRequestMethods requestMethod,
                                          Map<String, String> inputData,
                                          int successCode,
                                          Class<T> expectedType) throws IOException {

        // Make Request
        HttpClient httpClient = new HttpClient();
        HttpResponse response = httpClient.request(requestUrl, requestMethod, headers, inputData);

        // Generate response object
        T obj = null;

        int statusCode = response.getCode();
        TwitchResponse<T> twitchResponse = new TwitchResponse<>(response);

        if (statusCode == successCode) {
            // Success! We have data from Twitch
            obj = parseResponse(response.getContent(), expectedType);
        } else {
            // Error! Parse error message
            Error error = parseResponse(response.getContent(), Error.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(obj);

        return twitchResponse;
    }

    private <T> T parseResponse(String responseContent, Class<T> expectedType) throws IOException {
        return objectMapper.readValue(responseContent, expectedType);
    }

    protected <T> TwitchResponse<T> requestDelete(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.DELETE, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestGet(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.GET, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPut(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.PUT, null, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPut(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.PUT, inputData, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPost(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.POST, inputData, successCode, expectedType);
    }

    protected <T> TwitchResponse<T> requestPost(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.POST, null, successCode, expectedType);
    }

    protected String getBaseUrl() {
        return baseUrl;
    }
}
