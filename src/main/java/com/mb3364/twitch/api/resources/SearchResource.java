package com.mb3364.twitch.api.resources;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.handlers.ChannelsResponseHandler;
import com.mb3364.twitch.api.handlers.GamesResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsResponseHandler;
import com.mb3364.twitch.api.models.SearchResultContainer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public void channels(final String query, final RequestParams params, final ChannelsResponseHandler handler) {
        String url = String.format("%s/search/channels", getBaseUrl());
        params.put("q", query);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    SearchResultContainer value = objectMapper.readValue(content, SearchResultContainer.class);
                    handler.onSuccess(value.getTotal(), value.getChannels());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of channel objects matching the search query.
     *
     * @param query   the search query
     * @param handler the response handler
     */
    public void channels(String query, ChannelsResponseHandler handler) {
        channels(query, new RequestParams(), handler);
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
    public void streams(final String query, final RequestParams params, final StreamsResponseHandler handler) {
        String url = String.format("%s/search/streams", getBaseUrl());
        params.put("q", query);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    SearchResultContainer value = objectMapper.readValue(content, SearchResultContainer.class);
                    handler.onSuccess(value.getTotal(), value.getStreams());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of stream objects matching the search query.
     *
     * @param query   the search query
     * @param handler the response handler
     */
    public void streams(final String query, final StreamsResponseHandler handler) {
        streams(query, new RequestParams(), handler);
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
    public void games(final String query, final RequestParams params, final GamesResponseHandler handler) {
        String url = String.format("%s/search/games", getBaseUrl());
        params.put("q", query);
        params.put("type", "suggest");

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    SearchResultContainer value = objectMapper.readValue(content, SearchResultContainer.class);
                    handler.onSuccess(value.getGames().size(), value.getGames());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of game objects matching the search query.
     *
     * @param query   the search query
     * @param handler the response handler
     */
    public void games(final String query, final GamesResponseHandler handler) {
        games(query, new RequestParams(), handler);
    }
}
