package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.handlers.ChannelsResponseHandler;
import com.mb3364.twitch.api.handlers.GamesResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsResponseHandler;
import com.mb3364.twitch.api.models.Error;
import com.mb3364.twitch.api.models.SearchResultContainer;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpResponse;
import com.mb3364.twitch.http.JsonParams;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * The {@link SearchResource} provides the functionality
 * to access the <code>/search</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class SearchResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public SearchResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a list of channel objects matching the search query.
     *
     * @param query   the search query
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *                <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void channels(String query, JsonParams params, ChannelsResponseHandler handler) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
            if (params == null) params = new JsonParams();
            String url = String.format("%s/search/channels?q=%s&%s", getBaseUrl(), query, params.toQueryString());

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                SearchResultContainer value =
                        objectMapper.readValue(response.getContent(), SearchResultContainer.class);
                handler.onSuccess(value.getTotal(), value.getChannels());
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void channels(String query, ChannelsResponseHandler handler) {
        channels(query, null, handler);
    }

    /**
     * Returns a list of stream objects matching the search query.
     *
     * @param query   the search query
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  the maximum number of objects in array. Maximum is 100.</li>
     *                <li><code>offset</code>: the object offset for pagination. Default is 0.</li>
     *                <li><code>hls</code>:  If set to true, only returns streams using HLS.
     *                If set to false, only returns streams that are non-HLS.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void streams(String query, JsonParams params, StreamsResponseHandler handler) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
            if (params == null) params = new JsonParams();
            String url = String.format("%s/search/streams?q=%s&%s",
                    getBaseUrl(), query, params.toQueryString());

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                SearchResultContainer value =
                        objectMapper.readValue(response.getContent(), SearchResultContainer.class);
                handler.onSuccess(value.getTotal(), value.getStreams());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void streams(String query, StreamsResponseHandler handler) {
        streams(query, null, handler);
    }

    /**
     * Returns a list of game objects matching the search query.
     *
     * @param query   the search query
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>live</code>:  If true, only returns games that are live on at least one channel.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void games(String query, JsonParams params, GamesResponseHandler handler) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
            if (params == null) params = new JsonParams();
            String url = String.format("%s/search/games?q=%s&type=suggest&%s",
                    getBaseUrl(), query, params.toQueryString());

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                SearchResultContainer value =
                        objectMapper.readValue(response.getContent(), SearchResultContainer.class);
                handler.onSuccess(value.getGames().size(), value.getGames());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void games(String query, GamesResponseHandler handler) {
        games(query, null, handler);
    }
}
