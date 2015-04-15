package api.root;

import api.TwitchResource;
import api.TwitchResponse;
import api.root.models.Root;
import api.root.models.Token;
import http.HttpResponse;

import java.util.List;

public class RootResource extends TwitchResource {

    public RootResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Token> get() {
        String url = String.format("%s/", getBaseUrl());

        TwitchResponse<Root> container = requestGet(url, HttpResponse.HTTP_OK, Root.class);

        // Create object with list rather than the container class
        TwitchResponse<Token> response = new TwitchResponse<Token>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getToken());
        }

        return response;
    }
}
