package api.resources;

import api.TwitchResponse;
import api.models.Root;
import api.models.Token;
import http.HttpResponse;

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
    public RootResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Authentication status. If you are authenticated, the response includes
     * the status of your token and links to other related resources.
     *
     * @return a TwitchResponse containing the {@link Token} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Token> get() throws IOException {
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
