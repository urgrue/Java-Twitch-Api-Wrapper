package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;

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
     * @param query  the search query
     * @param limit  the maximum number of results to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a {@link SearchResult}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<SearchResult<Channel>> channels(String query, int limit, int offset) throws IOException {
        String url = String.format("%s/search/channels?q=%s&limit=%s&offset=%s",
                getBaseUrl(), query, limit, offset);

        TwitchResponse<SearchResultContainer> c =
                requestGet(url, HttpResponse.HTTP_OK, SearchResultContainer.class);

        // Extract relevant results
        SearchResult<Channel> r = new SearchResult<Channel>();
        if (c.getObject() != null) {
            r.setTotal(c.getObject().getTotal());
            r.setResults(c.getObject().getChannels());
        }

        // Create object with search result rather than the container class
        TwitchResponse<SearchResult<Channel>> response = new TwitchResponse<SearchResult<Channel>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            response.setObject(r);
        }

        return response;
    }

    /**
     * Returns a list of stream objects matching the search query.
     *
     * @param query  the search query
     * @param limit  the maximum number of results to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a {@link SearchResult}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<SearchResult<Stream>> streams(String query, int limit, int offset) throws IOException {
        String url = String.format("%s/search/streams?q=%s&limit=%s&offset=%s",
                getBaseUrl(), query, limit, offset);

        TwitchResponse<SearchResultContainer> c =
                requestGet(url, HttpResponse.HTTP_OK, SearchResultContainer.class);

        // Extract relevant results
        SearchResult<Stream> r = new SearchResult<Stream>();
        if (c.getObject() != null) {
            r.setTotal(c.getObject().getTotal());
            r.setResults(c.getObject().getStreams());
        }

        // Create object with search result rather than the container class
        TwitchResponse<SearchResult<Stream>> response = new TwitchResponse<SearchResult<Stream>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            response.setObject(r);
        }

        return response;
    }

    /**
     * Returns a list of game objects matching the search query.
     *
     * @param query the search query
     * @param live  set <code>true</code> to only return live channels
     * @return a TwitchResponse containing a {@link SearchResult}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<SearchResult<Game>> games(String query, boolean live) throws IOException {
        String url = String.format("%s/search/games?q=%s&type=%s&live=%s",
                getBaseUrl(), query, "suggest", live);

        TwitchResponse<SearchResultContainer> c =
                requestGet(url, HttpResponse.HTTP_OK, SearchResultContainer.class);

        // Extract relevant results
        SearchResult<Game> r = new SearchResult<Game>();
        if (c.getObject() != null) {
            r.setTotal(c.getObject().getTotal());
            r.setResults(c.getObject().getGames());
        }

        // Create object with search result rather than the container class
        TwitchResponse<SearchResult<Game>> response = new TwitchResponse<SearchResult<Game>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            response.setObject(r);
        }

        return response;
    }

    /**
     * Returns a list of game objects matching the search query.
     *
     * @param query the search query
     * @return a TwitchResponse containing a {@link SearchResult}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<SearchResult<Game>> games(String query) throws IOException {
        return games(query, false);
    }
}
