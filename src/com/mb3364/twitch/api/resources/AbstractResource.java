package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpRequestMethods;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractResource is the abstract base class of a Twitch resource.
 * A resource provides the functionality to access the REST endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public abstract class AbstractResource {

    private static final ObjectMapper objectMapper = new ObjectMapper(); // can reuse, share globally
    private final String baseUrl; // Base url for twitch rest api
    private Map<String, String> headers = new HashMap<>(); // http headers

    /**
     * Construct a resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    protected AbstractResource(String baseUrl, String apiVersion) {
        this.baseUrl = baseUrl;
        headers.put("ACCEPT", "application/vnd.twitchtv." + apiVersion + "+json"); // Specify API version
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
     * Make a HTTP request to Twitch.tv and generate a {@link TwitchResponse} object.
     *
     * @param requestUrl    the URL of the resource endpoint to make request to
     * @param requestMethod the HTTP request method
     * @param successCode   the HTTP status code that signifies a successful response
     * @param expectedType  the expected class of the containing object result
     * @return {@link TwitchResponse} containing request response
     * @throws IOException if an error occurred during the request or while parsing the response
     * @see HttpRequestMethods
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
            obj = objectMapper.readValue(response.getContent(), expectedType);
        } else {
            // Error! Parse error message
            Error error = objectMapper.readValue(response.getContent(), Error.class);
            twitchResponse.setErrorMessage(error.getMessage());
        }

        twitchResponse.setObject(obj);

        return twitchResponse;
    }

    /**
     * Make a HTTP DELETE request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestDelete(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.DELETE, null, successCode, expectedType);
    }

    /**
     * Make a HTTP GET request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestGet(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.GET, null, successCode, expectedType);
    }

    /**
     * Make a HTTP PUT request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestPut(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.PUT, null, successCode, expectedType);
    }

    /**
     * Make a HTTP PUT request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param inputData    a key-value pair of data to send as input
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestPut(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.PUT, inputData, successCode, expectedType);
    }

    /**
     * Make a HTTP POST request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param inputData    a key-value pair of data to send as input
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestPost(String requestUrl, Map<String, String> inputData, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.POST, inputData, successCode, expectedType);
    }

    /**
     * Make a HTTP POST request and parse the response.
     *
     * @param requestUrl   the URL to request
     * @param successCode  the HTTP status code representing a successful response
     * @param expectedType the expected type of the object mapped response
     * @return a {@link TwitchResponse} containing response data from the request
     * @throws IOException if an error occurred during the request or while parsing the response
     */
    protected <T> TwitchResponse<T> requestPost(String requestUrl, int successCode, Class<T> expectedType) throws IOException {
        return request(requestUrl, HttpRequestMethods.POST, null, successCode, expectedType);
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
