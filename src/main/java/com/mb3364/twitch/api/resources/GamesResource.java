package main.java.com.mb3364.twitch.api.resources;

import main.java.com.mb3364.twitch.api.handlers.TopGamesResponseHandler;
import main.java.com.mb3364.twitch.api.models.*;
import main.java.com.mb3364.twitch.http.HttpClient;
import main.java.com.mb3364.twitch.api.models.Error;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.http.JsonParams;
import main.java.com.mb3364.twitch.api.models.Games;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.http.JsonParams;

import java.io.IOException;

/**
 * The {@link GamesResource} provides the functionality
 * to access the <code>/games</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class GamesResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public GamesResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of games objects sorted by number of current viewers on Twitch, most popular first.
     *
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *                <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void getTop(JsonParams params, TopGamesResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/games/top?%s", getBaseUrl(), params.toQueryString());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Games value = objectMapper.readValue(response.getContent(), Games.class);
                handler.onSuccess(value.getTotal(), value.getTop());
            } else {
                main.java.com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void getTop(TopGamesResponseHandler handler) {
        getTop(null, handler);
    }
}
