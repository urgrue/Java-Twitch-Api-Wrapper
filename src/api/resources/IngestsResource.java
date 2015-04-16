package api.resources;

import api.TwitchResponse;
import api.models.Ingest;
import api.models.Ingests;
import http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class IngestsResource extends AbstractResource {

    public IngestsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

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
