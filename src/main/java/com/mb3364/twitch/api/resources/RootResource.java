package main.java.com.mb3364.twitch.api.resources;

import main.java.com.mb3364.twitch.api.handlers.TokenResponseHandler;
import main.java.com.mb3364.twitch.api.models.*;
import main.java.com.mb3364.twitch.http.HttpClient;
import main.java.com.mb3364.twitch.api.models.Error;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;

/**
 * The {@link RootResource} provides the functionality
 * to access the root <code>/</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class RootResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public RootResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Authentication status. If you are authenticated, the response includes
     * the status of your token and links to other related resources.
     *
     * @param handler the response handler
     */
    public void get(TokenResponseHandler handler) {
        String url = String.format("%s/", getBaseUrl());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Root value = objectMapper.readValue(response.getContent(), Root.class);
                handler.onSuccess(value.getToken());
            } else {
                main.java.com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }
}
