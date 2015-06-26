package com.mb3364.twitch.api.resources;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.handlers.TeamResponseHandler;
import com.mb3364.twitch.api.handlers.TeamsResponseHandler;
import com.mb3364.twitch.api.models.Team;
import com.mb3364.twitch.api.models.Teams;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public TeamsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of active teams.
     *
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *                <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void get(final RequestParams params, final TeamsResponseHandler handler) {
        String url = String.format("%s/teams", getBaseUrl());

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Teams value = objectMapper.readValue(content, Teams.class);
                    handler.onSuccess(value.getTeams());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of active teams.
     *
     * @param handler the response handler
     */
    public void get(final TeamsResponseHandler handler) {
        get(new RequestParams(), handler);
    }

    /**
     * Returns a specified {@link Team} object.
     *
     * @param team    the name of the {@link Team}
     * @param handler the response handler
     */
    public void get(final String team, final TeamResponseHandler handler) {
        String url = String.format("%s/teams/%s", getBaseUrl(), team);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Team value = objectMapper.readValue(content, Team.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
