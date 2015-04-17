package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

/**
 * The {@link StreamsResource} provides the functionality
 * to access the <code>/streams</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class StreamsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public StreamsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a stream object.
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing a {@link Stream}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Stream> get(String channelName) throws IOException {
        String url = String.format("%s/streams/%s", getBaseUrl(), channelName);

        TwitchResponse<StreamContainer> c = requestGet(url, HttpResponse.HTTP_OK, StreamContainer.class);

        // Create object rather than the container class
        TwitchResponse<Stream> r = new TwitchResponse<Stream>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getStream());
        }

        // Put an empty stream object if the stream is not online
        if (r.getObject() == null && r.getErrorMessage() == null) {
            r.setObject(new Stream());
        }

        return r;
    }

    /**
     * Returns a list of stream objects that are queried by a number of parameters
     * sorted by number of viewers descending.
     * <p/>
     * Setting <code>game</code>, <code>channels</code>, or <code>clientId</code> to
     * <code>null</code> will ignore those parameters.
     *
     * @param game     the game to return streams for
     * @param channels a list of streams to return
     * @param limit    the maximum number of stream's to return
     * @param offset   the object offset for pagination
     * @param clientId the clientId of streams to return
     * @return a TwitchResponse containing a {@link Streams} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Streams> get(String game, List<String> channels, int limit, int offset, String clientId) throws IOException {
        // Concat channels into comma-separated list
        String cs = "";
        if (channels != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : channels) {
                sb.append(s).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            cs = sb.toString();
        }

        // Convert all null values to empty strings
        if (game == null) game = "";
        if (clientId == null) clientId = "";

        String ls = Integer.toString(limit);
        if (limit <= 0) {
            ls = "";
        }

        String url = String.format("%s/streams?game=%s&channel=%s&limit=%s&offset=%s&client_id=%s",
                getBaseUrl(), game, cs, ls, offset, clientId);

        return requestGet(url, HttpResponse.HTTP_OK, Streams.class);
    }

    /**
     * Returns a list of featured (promoted) stream objects.
     *
     * @param limit  the maximum number of stream's to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a list of {@link FeaturedStream}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<FeaturedStream>> getFeatured(int limit, int offset) throws IOException {
        String url = String.format("%s/streams/featured", getBaseUrl());

        TwitchResponse<FeaturedStreamContainer> c = requestGet(url, HttpResponse.HTTP_OK, FeaturedStreamContainer.class);

        // Create object rather than the container class
        TwitchResponse<List<FeaturedStream>> r = new TwitchResponse<List<FeaturedStream>>(
                c.getStatusCode(),
                c.getStatusText(),
                c.getErrorMessage());

        if (!c.hasError()) {
            r.setObject(c.getObject().getFeatured());
        }

        return r;
    }

    /**
     * Returns a summary of current streams.
     *
     * @param game the game to return stats for
     * @return a TwitchResponse containing a {@link StreamsSummary}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<StreamsSummary> getSummary(String game) throws IOException {
        String url = String.format("%s/streams/summary?game=%s", getBaseUrl(), game);
        return requestGet(url, HttpResponse.HTTP_OK, StreamsSummary.class);
    }

    /**
     * Returns a summary of current streams.
     *
     * @return a TwitchResponse containing a {@link StreamsSummary}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<StreamsSummary> getSummary() throws IOException {
        String url = String.format("%s/streams/summary", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, StreamsSummary.class);
    }

    /**
     * Returns a list of stream objects that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param limit  the maximum number of stream's to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a {@link Streams}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Streams> getFollowed(int limit, int offset) throws IOException {
        String url = String.format("%s/streams/followed?limit=%s&offset=%s",
                getBaseUrl(), limit, offset);

        return requestGet(url, HttpResponse.HTTP_OK, Streams.class);
    }

    /**
     * Returns a list of stream objects that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @param limit the maximum number of stream's to return
     * @return a TwitchResponse containing a {@link Streams}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Streams> getFollowed(int limit) throws IOException {
        return getFollowed(limit, 0);
    }

    /**
     * Returns a list of stream objects that the authenticated user is following.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @return a TwitchResponse containing a {@link Streams}
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Streams> getFollowed() throws IOException {
        return getFollowed(25, 0);
    }
}
