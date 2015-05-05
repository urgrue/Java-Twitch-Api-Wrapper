package com.mb3364.twitch.api.resources;

import com.mb3364.http.HttpResponse;
import com.mb3364.twitch.api.handlers.IngestsResponseHandler;
import com.mb3364.twitch.api.models.Ingests;

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
    public void get(final IngestsResponseHandler handler) {
        String url = String.format("%s/ingests", getBaseUrl());

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(HttpResponse response) {
                try {
                    Ingests value = objectMapper.readValue(response.getContent(), Ingests.class);
                    handler.onSuccess(value.getIngests());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
