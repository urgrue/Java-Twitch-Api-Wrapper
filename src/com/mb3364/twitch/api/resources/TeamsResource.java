package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.Team;
import com.mb3364.twitch.api.models.Teams;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

/**
 * The {@link TeamsResource} provides the functionality
 * to access the <code>/teams</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class TeamsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public TeamsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of active teams.
     *
     * @param limit  the maximum number of {@link Team}'s to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a list of {@link Team} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Team>> get(int limit, int offset) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/teams/?limit=%s&offset=%s", getBaseUrl(), limit, offset);
        TwitchResponse<Teams> container = requestGet(url, HttpResponse.HTTP_OK, Teams.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Team>> response = new TwitchResponse<List<Team>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getTeams());
        }

        return response;
    }

    /**
     * Returns a list of active teams.
     *
     * @param limit the maximum number of {@link Team}'s to return
     * @return a TwitchResponse containing a list of {@link Team} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Team>> get(int limit) throws IOException {
        return get(limit, 0);
    }

    /**
     * Returns a list of active teams.
     *
     * @return a TwitchResponse containing a list of {@link Team} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Team>> get() throws IOException {
        return get(25, 0);
    }

    /**
     * Returns a specified {@link Team} object.
     *
     * @param teamName the name of the {@link Team}
     * @return a TwitchResponse containing a {@link Team}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Team> get(String teamName) throws IOException {
        String url = String.format("%s/teams/%s", getBaseUrl(), teamName);
        return requestGet(url, HttpResponse.HTTP_OK, Team.class);
    }
}
