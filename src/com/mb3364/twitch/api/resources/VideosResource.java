package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.Video;
import com.mb3364.twitch.api.models.Videos;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

/**
 * The {@link VideosResource} provides the functionality
 * to access the <code>/videos</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class VideosResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public VideosResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Get a {@link Video} based on the <code>ID</code>.
     *
     * @param id the ID of the video
     * @return a TwitchResponse containing the {@link Video} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Video> get(String id) throws IOException {
        String url = String.format("%s/videos/%s", getBaseUrl(), id);
        return requestGet(url, HttpResponse.HTTP_OK, Video.class);
    }

    /**
     * Returns a list of {@link Video}'s created in a given time period sorted by number of views, most popular first.
     * The <code>game</code> and <code>period</code> parameters can be set <code>null</code> to ignore them.
     *
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @param offset Object offset for pagination. Default is 0
     * @param game   Returns only videos from game
     * @param period Returns only videos created in time period.
     *               Valid values are <code>week</code>, <code>month</code>, or <code>all</code>
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTop(int limit, int offset, String game, String period) throws IOException {
        String url = String.format("%s/videos/top?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        if (game != null) {
            url += "&game=" + game;
        }

        if (period != null) {
            url += "&period=" + period;
        }

        TwitchResponse<Videos> c = requestGet(url, HttpResponse.HTTP_OK, Videos.class);

        // Create object rather than the container class
        TwitchResponse<List<Video>> r = new TwitchResponse<List<Video>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getVideos());
        }

        return r;
    }

    /**
     * Returns a list of {@link Video}'s created in a given time period sorted by number of views, most popular first.
     *
     * @param offset Object offset for pagination. Default is 0
     *               Valid values are <code>week</code>, <code>month</code>, or <code>all</code>
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTop(int limit, int offset) throws IOException {
        return getTop(limit, offset, null, null);
    }

    /**
     * Returns a list of {@link Video}'s by <code>game</code>, created in a given time period
     * sorted by number of views, most popular first.
     *
     * @param game   Returns only videos from game
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @param offset Object offset for pagination. Default is 0
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTopByGame(String game, int limit, int offset) throws IOException {
        return getTop(limit, offset, game, null);
    }

    /**
     * Returns a list of {@link Video}'s by <code>game</code>, sorted by number of views, most popular first.
     *
     * @param game  Returns only videos from game
     * @param limit the maximum number of objects in array. Default is 10. Maximum is 100
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTopByGame(String game, int limit) throws IOException {
        return getTop(limit, 0, game, null);
    }

    /**
     * Returns a list of {@link Video}'s created in a given time period sorted by number of views, most popular first.
     *
     * @param period Returns only videos created in time period.
     *               Valid values are <code>week</code>, <code>month</code>, or <code>all</code>
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @param offset Object offset for pagination. Default is 0
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTopByPeriod(String period, int limit, int offset) throws IOException {
        return getTop(limit, offset, null, period);
    }

    /**
     * Returns a list of {@link Video}'s created in a given time period sorted by number of views, most popular first.
     *
     * @param period Returns only videos created in time period.
     *               Valid values are <code>week</code>, <code>month</code>, or <code>all</code>
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getTopByPeriod(String period, int limit) throws IOException {
        return getTop(limit, 0, null, period);
    }

    /**
     * Returns a list of {@link Video}'s from channels that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param limit  the maximum number of objects in array. Default is 10. Maximum is 100
     * @param offset Object offset for pagination. Default is 0
     * @return a TwitchResponse containing a list of {@link Video}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Video>> getFollowed(int limit, int offset) throws IOException {
        String url = String.format("%s/videos/followed?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        TwitchResponse<Videos> c = requestGet(url, HttpResponse.HTTP_OK, Videos.class);

        // Create object rather than the container class
        TwitchResponse<List<Video>> r = new TwitchResponse<List<Video>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getVideos());
        }

        return r;
    }
}
