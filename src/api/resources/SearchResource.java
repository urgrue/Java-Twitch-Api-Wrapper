package api.resources;

import api.TwitchResponse;
import api.models.*;
import http.HttpResponse;

import java.io.IOException;

public class SearchResource extends AbstractResource {

    public SearchResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

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

    public TwitchResponse<SearchResult<Game>> games(String query) throws IOException {
        return games(query, false);
    }
}
