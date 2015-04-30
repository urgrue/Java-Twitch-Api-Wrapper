package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.handlers.VideoResponseHandler;
import com.mb3364.twitch.api.handlers.VideosResponseHandler;
import com.mb3364.twitch.api.models.Video;
import com.mb3364.twitch.api.models.Videos;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpResponse;
import com.mb3364.twitch.http.JsonParams;

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
     * Returns a {@link Video} object.
     *
     * @param id the ID of the Video
     * @param handler the response handler
     */
    public void get(String id, VideoResponseHandler handler) {
        String url = String.format("%s/videos/%s", getBaseUrl(), id);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Video value = objectMapper.readValue(response.getContent(), Video.class);
                handler.onSuccess(value);
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of {@link Video}'s created in a given time period sorted by number of views, most popular first.
     *
     * @param params the optional request parameters:
     *               <ul>
     *               <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *               <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *               <li><code>game</code>: Returns only videos from game.</li>
     *               <li><code>period</code>: Returns only videos created in time period.
     *                                        Valid values are <code>week</code>, <code>month</code>,
     *                                        or <code>all</code>. Default is <code>week</code>.
     *               </li>
     *               </ul>
     *
     * @param handler the response handler
     */
    public void getTop(JsonParams params, VideosResponseHandler handler) {
        try {
            if (params == null) params = new JsonParams();
            String url = String.format("%s/videos/top?%s", getBaseUrl(), params.toQueryString());

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Videos value = objectMapper.readValue(response.getContent(), Videos.class);
                handler.onSuccess(value.getVideos().size(), value.getVideos());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void getTop(VideosResponseHandler handler) {
        getTop(null, handler);
    }

    /**
     * Returns a list of {@link Video}'s from channels that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param params the optional request parameters:
     *               <ul>
     *               <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *               <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *               </ul>
     * @param handler the response handler
     */
    public void getFollowed(JsonParams params, VideosResponseHandler handler) {
        try {
            if (params == null) params = new JsonParams();
            String url = String.format("%s/videos/followed?%s", getBaseUrl(), params.toQueryString());

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Videos value = objectMapper.readValue(response.getContent(), Videos.class);
                handler.onSuccess(value.getVideos().size(), value.getVideos());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), com.mb3364.twitch.api.models.Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void getFollowed(VideosResponseHandler handler) {
        getFollowed(null, handler);
    }
}
