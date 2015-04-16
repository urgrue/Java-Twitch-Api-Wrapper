package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@link ChannelsResource} provides the functionality
 * to access the <code>/channels</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class ChannelsResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public ChannelsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a channel object.
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing the channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> get(String channelName) throws IOException {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    /**
     * Returns a channel object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_READ}</p>
     *
     * @return a TwitchResponse containing the channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> get() throws IOException {
        String url = String.format("%s/channel", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    /**
     * Returns a list of user objects who are editors of <code>channelName</code>.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_READ}</p>
     *
     * @param channelName the name of the channel
     * @return a list of user objects who are editors of <code>channelName</code>
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<List<User>> getEditors(String channelName) throws IOException {
        String url = String.format("%s/channels/%s/editors", getBaseUrl(), channelName);
        TwitchResponse<Editors> container = requestGet(url, HttpResponse.HTTP_OK, Editors.class);

        // Create object with list rather than the container class
        TwitchResponse<List<User>> response = new TwitchResponse<List<User>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getUsers());
        }

        return response;
    }

    /**
     * Update channel's status, game, and delay.
     * <ul>
     * <li>Setting status or game to <code>null</code> will ignore them when updating the channel.</li>
     * <li>Setting delay to <code>-1</code> will ignore this parameter when updating the channel.</li>
     * </ul>
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the channel
     * @param status      the channel's title
     * @param game        the game category to be classified as
     * @param delay       the channel's delay in seconds. Requires the channel owner's OAuth token
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> set(String channelName, String status, String game, int delay) throws IOException {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        Map<String, String> params = new HashMap<String, String>();
        if (status != null) params.put("channel[status]", status);
        if (game != null) params.put("channel[game]", game);
        if (delay >= 0) params.put("channel[delay]", Integer.toString(delay));

        return requestPut(url, params, HttpResponse.HTTP_OK, Channel.class);
    }

    /**
     * Update channel's status and game.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the channel
     * @param status      the channel's title
     * @param game        the game category to be classified as
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> setStatusAndGame(String channelName, String status, String game) throws IOException {
        return set(channelName, status, game, -1);
    }

    /**
     * Update channel's status.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the channel
     * @param status      the channel's title
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> setStatus(String channelName, String status) throws IOException {
        return set(channelName, status, null, -1);
    }

    /**
     * Update channel's game.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the channel
     * @param game        the game category to be classified as
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> setGame(String channelName, String game) throws IOException {
        return set(channelName, null, game, -1);
    }

    /**
     * Update channel's delay.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the channel
     * @param delay       the channel's delay in seconds. Requires the channel owner's OAuth token
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> setDelay(String channelName, int delay) throws IOException {
        return set(channelName, null, null, delay);
    }

    /**
     * Reset channel's stream key.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_STREAM}</p>
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing the updated channel or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Channel> resetStreamKey(String channelName) throws IOException {
        String url = String.format("%s/channels/%s/stream_key", getBaseUrl(), channelName);
        return requestDelete(url, HttpResponse.HTTP_OK, Channel.class);
    }

    /**
     * Start a commercial on channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_COMMERCIAL}</p>
     *
     * @param channelName the name of the channel
     * @param length      the length in seconds. Valid values are 30, 60, 90, 120, 150, and 180
     * @return a TwitchResponse containing an {@link Empty} object or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Empty> startCommercial(String channelName, int length) throws IOException {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);
        Map<String, String> input = new HashMap<String, String>();
        input.put("length", Integer.toString(length));
        return requestPost(url, input, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

    /**
     * Start a default length commercial on channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_COMMERCIAL}</p>
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing an {@link Empty} object or an error
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Empty> startCommercial(String channelName) throws IOException {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);
        return requestPost(url, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

    /**
     * Returns a list of team objects a channel belongs to.
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing a list of {@link Team}'s
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<List<Team>> getTeams(String channelName) throws IOException {
        String url = String.format("%s/channels/%s/teams", getBaseUrl(), channelName);
        TwitchResponse<Teams> container = requestGet(url, HttpResponse.HTTP_OK, Teams.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Team>> response = new TwitchResponse<List<Team>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getTeams());
        }

        return response;
    }

    /**
     * Returns a list of channel follow objects.
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @param direction   the order, by creation date. Valid values are <code>asc</code> and <code>desc</code>
     * @return a TwitchResponse containing a {@link ChannelFollows} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelFollows> getFollows(String channelName, int limit, int offset, String direction) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/channels/%s/follows?limit=%s&offset=%s&direction=%s",
                getBaseUrl(), channelName, limit, offset, direction);

        return requestGet(url, HttpResponse.HTTP_OK, ChannelFollows.class);
    }

    /**
     * Returns a list of videos ordered by time of creation, starting with
     * the most recent from specified channel.
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @param broadcasts  set to <code>true</code> to return only broadcasts, otherwise highlights are returned
     * @param hls         set to <code>true</code> to return only HLS VoDs
     * @return a TwitchResponse containing a {@link Videos} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Videos> getVideos(String channelName, int limit, int offset, boolean broadcasts, boolean hls) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/channels/%s/videos?limit=%s&offset=%s&broadcasts=%s&hls=%s",
                getBaseUrl(), channelName, limit, offset, broadcasts, hls);

        return requestGet(url, HttpResponse.HTTP_OK, Videos.class);
    }

    /**
     * Returns a list of video highlights ordered by time of creation, starting with
     * the most recent from specified channel.
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @return a TwitchResponse containing a {@link Videos} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Videos> getVideoHighlights(String channelName, int limit, int offset) throws IOException {
        return getVideos(channelName, limit, offset, false, false);
    }

    /**
     * Returns a list of video broadcasts ordered by time of creation, starting with
     * the most recent from specified channel.
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @return a TwitchResponse containing a {@link Videos} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<Videos> getVideoBroadcasts(String channelName, int limit, int offset) throws IOException {
        return getVideos(channelName, limit, offset, true, false);
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @param direction   the order, by creation date. Valid values are <code>asc</code> and <code>desc</code>
     * @return a TwitchResponse containing a {@link ChannelSubscriptions} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit, int offset, String direction) throws IOException {
        String url = String.format("%s/channels/%s/subscriptions?limit=%s&offset=%s&direction=%s",
                getBaseUrl(), channelName, limit, offset, direction);

        return requestGet(url, HttpResponse.HTTP_OK, ChannelSubscriptions.class);
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @param offset      the offset for pagination
     * @return a TwitchResponse containing a {@link ChannelSubscriptions} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit, int offset) throws IOException {
        return getSubscriptions(channelName, limit, offset, "asc");
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the channel
     * @param limit       the maximum number of objects in the array, maximum is 100
     * @return a TwitchResponse containing a {@link ChannelSubscriptions} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit) throws IOException {
        return getSubscriptions(channelName, limit, 0);
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the channel
     * @return a TwitchResponse containing a {@link ChannelSubscriptions} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName) throws IOException {
        return getSubscriptions(channelName, 25);
    }

    /**
     * Returns a subscription object which includes the user if that user is subscribed.
     * <p>Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#CHANNEL_CHECK_SUBSCRIPTION}</p>
     *
     * @param channelName the name of the channel
     * @param user        the user to check
     * @return a TwitchResponse containing a {@link ChannelSubscription} object
     * @throws IOException if an error occurs making the request
     */
    public TwitchResponse<ChannelSubscription> getSubscription(String channelName, String user) throws IOException {
        String url = String.format("%s/channels/%s/subscriptions/%s",
                getBaseUrl(), channelName, user);

        return requestGet(url, HttpResponse.HTTP_OK, ChannelSubscription.class);
    }
}
