package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.Games;
import com.mb3364.twitch.http.HttpResponse;

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
    public GamesResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of games objects sorted by number of current viewers on Twitch, most popular first.
     *
     * @param limit  the maximum number of objects in array. Maximum is 100.
     * @param offset the object offset for pagination. Default is 0.
     * @return a TwitchResponse containing a {@link Games} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Games> get(int limit, int offset) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/games/top?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        return requestGet(url, HttpResponse.HTTP_OK, Games.class);
    }

    /**
     * Returns a list of games objects sorted by number of current viewers on Twitch, most popular first.
     *
     * @param limit the maximum number of objects in array. Maximum is 100.
     * @return a TwitchResponse containing a {@link Games} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Games> get(int limit) throws IOException {
        return get(limit, 0);
    }

    /**
     * Returns a list of games objects sorted by number of current viewers on Twitch, most popular first.
     *
     * @return a TwitchResponse containing a {@link Games} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Games> get() throws IOException {
        return get(10, 0);
    }
}
