package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.Ingest;
import com.mb3364.twitch.api.models.Ingests;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

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
     * @return a TwitchResponse containing a list of {@link Ingest} objects
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Ingest>> get() throws IOException {
        String url = String.format("%s/ingests", getBaseUrl());

        TwitchResponse<Ingests> container = requestGet(url, HttpResponse.HTTP_OK, Ingests.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Ingest>> response = new TwitchResponse<List<Ingest>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getIngests());
        }

        return response;
    }
}
