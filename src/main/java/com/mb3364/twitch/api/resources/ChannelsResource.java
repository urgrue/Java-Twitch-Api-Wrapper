package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.auth.Scopes;
import com.mb3364.twitch.api.handlers.*;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.api.models.Error;
import com.mb3364.twitch.http.HttpClient;
import com.mb3364.twitch.http.HttpResponse;
import com.mb3364.twitch.http.JsonParams;

import java.io.IOException;
import java.util.HashMap;
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
    public ChannelsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a channel object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_READ}</p>
     *
     * @param handler the response handler
     */
    public void get(ChannelResponseHandler handler) {
        String url = String.format("%s/channel", getBaseUrl());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Channel value = objectMapper.readValue(response.getContent(), Channel.class);
                handler.onSuccess(value);
            } else {
                com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a {@link Channel} object.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void get(String channelName, ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Channel value = objectMapper.readValue(response.getContent(), Channel.class);
                handler.onSuccess(value);
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of user objects who are editors of <code>channelName</code>.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_READ}</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getEditors(String channelName, UsersResponseHandler handler) {
        String url = String.format("%s/channels/%s/editors", getBaseUrl(), channelName);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Editors value = objectMapper.readValue(response.getContent(), Editors.class);
                handler.onSuccess(value.getUsers());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Update channel's status, game, or delay.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_EDITOR}</p>
     *
     * @param channelName the name of the Channel
     * @param params      the optional request parameters:
     *                    <ul>
     *                    <li><code>status</code>: Channel's title</li>
     *                    <li><code>game</code>: Game category to be classified as.</li>
     *                    <li><code>delay</code>: Channel delay in seconds. Requires the channel owner's OAuth token.</li>
     *                    </ul>
     * @param handler     the response handler
     */
    public void put(String channelName, JsonParams params, ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        // Convert to an object string
        if (params == null) params = new JsonParams();

        if (params.containsKey("status")) {
            params.put("channel[status]", params.get("status"));
            params.remove("status");
        }

        if (params.containsKey("game")) {
            params.put("channel[game]", params.get("game"));
            params.remove("game");
        }

        if (params.containsKey("delay")) {
            params.put("channel[delay]", params.get("delay"));
            params.remove("delay");
        }

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.put(url, headers, params);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Channel value = objectMapper.readValue(response.getContent(), Channel.class);
                handler.onSuccess(value);
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Reset channel's stream key.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_STREAM}</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void resetStreamKey(String channelName, ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s/stream_key", getBaseUrl(), channelName);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.delete(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Channel value = objectMapper.readValue(response.getContent(), Channel.class);
                handler.onSuccess(value);
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Start a commercial on channel.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_COMMERCIAL}</p>
     *
     * @param channelName the name of the channel
     * @param length      Length of commercial break in seconds. Default value is <code>30</code>.
     *                    Valid values are <code>30</code>, <code>60</code>, <code>90</code>,
     *                    <code>120</code>, <code>150</code>, and <code>180</code>
     * @param handler     the response handler
     */
    public void startCommercial(String channelName, int length, CommercialResponseHandler handler) {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);
        Map<String, String> input = new HashMap<String, String>();
        input.put("length", Integer.toString(length));

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.post(url, headers, input);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_NO_CONTENT) {
                handler.onSuccess();
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of team objects the channel belongs to.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getTeams(String channelName, TeamsResponseHandler handler) {
        String url = String.format("%s/channels/%s/teams", getBaseUrl(), channelName);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Teams value = objectMapper.readValue(response.getContent(), Teams.class);
                handler.onSuccess(value.getTeams());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * @param channelName the name of the Channel
     * @param params      the optional request parameters:
     *                    <ul>
     *                    <li><code>limit</code>: Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                    <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                    <li><code>direction</code>: Creation date sorting direction. Default is <code>desc</code>.
     *                    Valid values are <code>asc</code> and <code>desc</code>.
     *                    </li>
     *                    </ul>
     * @param handler     the response handler
     */
    public void getFollows(String channelName, JsonParams params, ChannelFollowsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/channels/%s/follows?%s",
                getBaseUrl(), channelName, params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                ChannelFollows value =
                        objectMapper.readValue(response.getContent(), ChannelFollows.class);
                handler.onSuccess(value.getTotal(), value.getFollows());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void getFollows(String channelName, ChannelFollowsResponseHandler handler) {
        getFollows(channelName, null, handler);
    }

    /**
     * Returns a list of videos ordered by time of creation, starting with
     * the most recent from specified channel.
     *
     * @param channelName the name of the Channel
     * @param params      the optional request parameters:
     *                    <ul>
     *                    <li><code>limit</code>: Maximum number of objects in array. Default is 10. Maximum is 100.</li>
     *                    <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                    <li><code>broadcasts</code>: Returns only broadcasts when <code>true</code>.
     *                    Otherwise only highlights are returned.
     *                    Default is <code>false</code>
     *                    </li>
     *                    <li><code>hls</code>: Returns only HLS VoDs when <code>true</code>.
     *                    Otherwise only non-HLS VoDs are returned.
     *                    Default is <code>false</code>.
     *                    </li>
     *                    </ul>
     * @param handler     the response handler
     */
    public void getVideos(String channelName, JsonParams params, VideosResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/channels/%s/videos?%s",
                getBaseUrl(), channelName, params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Videos value = objectMapper.readValue(response.getContent(), Videos.class);
                handler.onSuccess(value.getTotal(), value.getVideos());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    public void getVideos(String channelName, VideosResponseHandler handler) {
        getVideos(channelName, null, handler);
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the Channel
     * @param params      the optional request parameters:
     *                    <ul>
     *                    <li><code>limit</code>: Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                    <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                    <li><code>direction</code>: Creation date sorting direction.
     *                    Default is <code>asc</code>. Valid values are <code>asc</code> and <code>desc</code>.
     *                    </li>
     *                    </ul>
     * @param handler     the response handler
     */
    public void getSubscriptions(String channelName, JsonParams params, ChannelSubscriptionsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/channels/%s/subscriptions?%s",
                getBaseUrl(), channelName, params.toQueryString());
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                ChannelSubscriptions value = objectMapper.readValue(response.getContent(), ChannelSubscriptions.class);
                handler.onSuccess(value.getTotal(), value.getSubscriptions());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a subscription object which includes the user if that user is subscribed.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_CHECK_SUBSCRIPTION}</p>
     *
     * @param channelName the name of the channel
     * @param user        the user to check
     * @param handler     the response handler
     */
    public void getSubscription(String channelName, String user, ChannelSubscriptionResponseHandler handler) {
        String url = String.format("%s/channels/%s/subscriptions/%s",
                getBaseUrl(), channelName, user);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                ChannelSubscription value = objectMapper.readValue(response.getContent(), ChannelSubscription.class);
                handler.onSuccess(value);
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }
}
