package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.handlers.TeamResponseHandler;
import com.mb3364.twitch.api.handlers.TeamsResponseHandler;
import com.mb3364.twitch.api.models.Team;
import com.mb3364.twitch.api.models.Teams;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpResponse;
import com.mb3364.twitch.http.JsonParams;

import java.io.IOException;
import java.net.URLEncoder;
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
    public TeamsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of active teams.
     *
     * @param params the optional request parameters:
     *               <ul>
     *               <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *               <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *               </ul>
     * @param handler the response handler
     */
    public void get(JsonParams params, TeamsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/teams?%s", getBaseUrl(), params.toQueryString());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Teams value = objectMapper.readValue(response.getContent(), Teams.class);
                handler.onSuccess(value.getTeams());
            } else {
                com.mb3364.twitch.api.models.Error error =
                        objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void get(TeamsResponseHandler handler) {
        get(null, handler);
    }

    /**
     * Returns a specified {@link Team} object.
     *
     * @param team the name of the {@link Team}
     * @param handler the response handler
     */
    public void get(String team, TeamResponseHandler handler) {
        try {
            team = URLEncoder.encode(team, "UTF-8");
            String url = String.format("%s/teams/%s", getBaseUrl(), team);

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Team value = objectMapper.readValue(response.getContent(), Team.class);
                handler.onSuccess(value);
            } else {
                com.mb3364.twitch.api.models.Error error =
                        objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }
}
