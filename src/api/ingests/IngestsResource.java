package api.ingests;

import api.TwitchResource;
import api.TwitchResponse;
import api.ingests.models.Ingest;
import api.ingests.models.Ingests;
import http.HttpResponse;

import java.util.List;

public class IngestsResource extends TwitchResource {

    public IngestsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<List<Ingest>> get() {
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
