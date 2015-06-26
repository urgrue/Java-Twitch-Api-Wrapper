package com.mb3364.twitch.api.resources;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.auth.Scopes;
import com.mb3364.twitch.api.handlers.*;
import com.mb3364.twitch.api.models.*;

import java.io.IOException;
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
    public ChannelsResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a channel object of authenticated user. Channel object includes stream key.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_READ}</p>
     *
     * @param handler the response handler
     */
    public void get(final ChannelResponseHandler handler) {
        String url = String.format("%s/channel", getBaseUrl());

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Channel value = objectMapper.readValue(content, Channel.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a {@link Channel} object.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void get(final String channelName, final ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Channel value = objectMapper.readValue(content, Channel.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of user objects who are editors of <code>channelName</code>.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_READ}</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getEditors(final String channelName, final UsersResponseHandler handler) {
        String url = String.format("%s/channels/%s/editors", getBaseUrl(), channelName);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Editors value = objectMapper.readValue(content, Editors.class);
                    handler.onSuccess(value.getUsers());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
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
    public void put(final String channelName, final RequestParams params, final ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s", getBaseUrl(), channelName);

        if (params.containsKey("status")) {
            params.put("channel[status]", params.getString("status"));
            params.remove("status");
        }

        if (params.containsKey("game")) {
            params.put("channel[game]", params.getString("game"));
            params.remove("game");
        }

        if (params.containsKey("delay")) {
            params.put("channel[delay]", params.getString("delay"));
            params.remove("delay");
        }

        http.put(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Channel value = objectMapper.readValue(content, Channel.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Reset channel's stream key.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_STREAM}</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void resetStreamKey(final String channelName, final ChannelResponseHandler handler) {
        String url = String.format("%s/channels/%s/stream_key", getBaseUrl(), channelName);

        http.delete(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Channel value = objectMapper.readValue(content, Channel.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
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
    public void startCommercial(final String channelName, final int length, final CommercialResponseHandler handler) {
        String url = String.format("%s/channels/%s/commercial", getBaseUrl(), channelName);

        RequestParams params = new RequestParams();
        params.put("length", Integer.toString(length));

        http.post(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                handler.onSuccess();
            }
        });
    }

    /**
     * Returns a list of team objects the channel belongs to.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getTeams(final String channelName, final TeamsResponseHandler handler) {
        String url = String.format("%s/channels/%s/teams", getBaseUrl(), channelName);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Teams value = objectMapper.readValue(content, Teams.class);
                    handler.onSuccess(value.getTeams());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of follow objects representing the followers of a channel.
     *
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
    public void getFollows(final String channelName, final RequestParams params, final ChannelFollowsResponseHandler handler) {
        String url = String.format("%s/channels/%s/follows", getBaseUrl(), channelName);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    ChannelFollows value = objectMapper.readValue(content, ChannelFollows.class);
                    handler.onSuccess(value.getTotal(), value.getFollows());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of follow objects representing the followers of a channel.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getFollows(final String channelName, final ChannelFollowsResponseHandler handler) {
        getFollows(channelName, new RequestParams(), handler);
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
    public void getVideos(final String channelName, final RequestParams params, final VideosResponseHandler handler) {
        String url = String.format("%s/channels/%s/videos", getBaseUrl(), channelName);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Videos value = objectMapper.readValue(content, Videos.class);
                    handler.onSuccess(value.getTotal(), value.getVideos());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of videos ordered by time of creation, starting with
     * the most recent from specified channel.
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getVideos(final String channelName, final VideosResponseHandler handler) {
        getVideos(channelName, new RequestParams(), handler);
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
    public void getSubscriptions(final String channelName, final RequestParams params, final ChannelSubscriptionsResponseHandler handler) {
        String url = String.format("%s/channels/%s/subscriptions", getBaseUrl(), channelName);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    ChannelSubscriptions value = objectMapper.readValue(content, ChannelSubscriptions.class);
                    handler.onSuccess(value.getTotal(), value.getSubscriptions());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of subscription objects sorted by subscription relationship creation date
     * which contain users subscribed to the specified channel.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_SUBSCRIPTIONS}</p>
     *
     * @param channelName the name of the Channel
     * @param handler     the response handler
     */
    public void getSubscriptions(final String channelName, final ChannelSubscriptionsResponseHandler handler) {
        getSubscriptions(channelName, new RequestParams(), handler);
    }

    /**
     * Returns a subscription object which includes the user if that user is subscribed.
     * <p>Authenticated, required scope: {@link Scopes#CHANNEL_CHECK_SUBSCRIPTION}</p>
     *
     * @param channelName the name of the channel
     * @param user        the user to check
     * @param handler     the response handler
     */
    public void getSubscription(final String channelName, final String user, final ChannelSubscriptionResponseHandler handler) {
        String url = String.format("%s/channels/%s/subscriptions/%s", getBaseUrl(), channelName, user);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    ChannelSubscription value = objectMapper.readValue(content, ChannelSubscription.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }
}
