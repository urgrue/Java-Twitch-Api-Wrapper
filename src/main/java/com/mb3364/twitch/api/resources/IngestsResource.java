package main.java.com.mb3364.twitch.api.resources;

import main.java.com.mb3364.twitch.api.handlers.IngestsResponseHandler;
import main.java.com.mb3364.twitch.api.models.Ingests;
import main.java.com.mb3364.twitch.http.HttpClient;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.api.handlers.IngestsResponseHandler;
import main.java.com.mb3364.twitch.api.models.*;
import main.java.com.mb3364.twitch.api.models.Error;
import main.java.com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;

/**
 * The {@link IngestsResource} provides the functionality
 * to access the <code>/ingests</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class IngestsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public IngestsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of ingest objects.
     *
     * @param handler the response handler
     */
    public void get(IngestsResponseHandler handler) {
        String url = String.format("%s/ingests", getBaseUrl());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Ingests value = objectMapper.readValue(response.getContent(), Ingests.class);
                handler.onSuccess(value.getIngests());
            } else {
                main.java.com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }
}
