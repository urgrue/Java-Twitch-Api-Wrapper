package api.resources;

import api.models.Empty;
import api.TwitchResponse;
import api.models.Channel;
import api.models.ChannelSubscription;
import api.models.ChannelSubscriptions;
import api.models.Editors;
import api.models.UserFollows;
import api.models.Team;
import api.models.Teams;
import api.models.User;
import api.models.Videos;
import http.HttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelsResource extends TwitchResource {

    public ChannelsResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Channel> get(String channelName) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    public TwitchResponse<Channel> get() {
        String url = String.format("%s/channel", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, Channel.class);
    }

    public TwitchResponse<List<User>> getEditors(String channelName) {
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

    public TwitchResponse<Channel> set(String channelName, String status, String game, int delay) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        Map<String, String> params = new HashMap<>();
        if (status != null) params.put("channel[status]", status);
        if (game != null) params.put("channel[game]", game);
        if (delay >= 0) params.put("channel[delay]", Integer.toString(delay));

        return requestPut(url, params, HttpResponse.HTTP_OK, Channel.class);
    }

    public TwitchResponse<Channel> setStatusAndGame(String channelName, String status, String game) {
        return set(channelName, status, game, -1);
    }

    public TwitchResponse<Channel> setStatus(String channelName, String status) {
        return set(channelName, status, null, -1);
    }

    public TwitchResponse<Channel> setGame(String channelName, String game) {
        return set(channelName, null, game, -1);
    }

    public TwitchResponse<Channel> setDelay(String channelName, int delay) {
        return set(channelName, null, null, delay);
    }

    public TwitchResponse<Channel> resetStreamKey(String channelName) {
        String url = String.format("%s/channels/%s/stream_key", getBaseUrl(), channelName);
        return requestDelete(url, HttpResponse.HTTP_OK, Channel.class);
    }

    /**
     * Start a commercial on channel.
     * @param channelName Name of the channel
     * @param length Length in seconds. Valid values are 30, 60, 90, 120, 150, and 180
     * @return <code>Empty</code> object signifying that there was not an error
     */
    public TwitchResponse<Empty> startCommercial(String channelName, int length) {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);
        Map<String, String> input = new HashMap<String, String>();
        input.put("length", Integer.toString(length));
        return requestPost(url, input, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

    /**
     * Start a default length commercial on channel.
     * @param channelName Name of the channel
     * @return <code>Empty</code> object signifying that there was not an error
     */
    public TwitchResponse<Empty> startCommercial(String channelName) {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);
        return requestPost(url, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

    public TwitchResponse<List<Team>> getTeams(String channelName) {
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

    public TwitchResponse<UserFollows> getFollows(String channelName, int limit, int offset, String direction){
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/channels/%s/follows?limit=%s&offset=%s&direction=%s",
                getBaseUrl(), channelName, limit, offset, direction);

        return requestGet(url, HttpResponse.HTTP_OK, UserFollows.class);
    }

    public TwitchResponse<Videos> getVideos(String channelName, int limit, int offset, boolean broadcasts, boolean hls) {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/channels/%s/videos?limit=%s&offset=%s&broadcasts=%s&hls=%s",
                getBaseUrl(), channelName, limit, offset, broadcasts, hls);

        return requestGet(url, HttpResponse.HTTP_OK, Videos.class);
    }

    public TwitchResponse<Videos> getVideoHighlights(String channelName, int limit, int offset) {
        return getVideos(channelName, limit, offset, false, false);
    }

    public TwitchResponse<Videos> getVideoBroadcasts(String channelName, int limit, int offset) {
        return getVideos(channelName, limit, offset, true, false);
    }

    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit, int offset, String direction) {
        String url = String.format("%s/channels/%s/subscriptions?limit=%s&offset=%s&direction=%s",
                getBaseUrl(), channelName, limit, offset, direction);

        return requestGet(url, HttpResponse.HTTP_OK, ChannelSubscriptions.class);
    }

    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit, int offset) {
        return getSubscriptions(channelName, limit, offset, "asc");
    }

    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName, int limit) {
        return getSubscriptions(channelName, limit, 0);
    }

    public TwitchResponse<ChannelSubscriptions> getSubscriptions(String channelName) {
        return getSubscriptions(channelName, 25);
    }

    public TwitchResponse<ChannelSubscription> getSubscription(String channelName, String user) {
        String url = String.format("%s/channels/%s/subscriptions/%s",
                getBaseUrl(), channelName, user);

        return requestGet(url, HttpResponse.HTTP_OK, ChannelSubscription.class);
    }
}
