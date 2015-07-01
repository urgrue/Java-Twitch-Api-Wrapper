package com.mb3364.twitch.api.resources;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.auth.Scopes;
import com.mb3364.twitch.api.handlers.*;
import com.mb3364.twitch.api.models.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@link UsersResource} provides the functionality
 * to access the <code>/users</code> endpoints of the Twitch API.
 *
 * @author Matthew Bell
 */
public class UsersResource extends AbstractResource {

    /**
     * Construct the resource using the Twitch API base URL and specified API version.
     *
     * @param baseUrl    the base URL of the Twitch API
     * @param apiVersion the requested version of the Twitch API
     */
    public UsersResource(String baseUrl, int apiVersion) {
        super(baseUrl, apiVersion);
    }

    /**
     * Returns a {@link User} object.
     *
     * @param user    the user to request
     * @param handler the response handler
     */
    public void get(final String user, final UserResponseHandler handler) {
        String url = String.format("%s/users/%s", getBaseUrl(), user);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    User value = objectMapper.readValue(content, User.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns the authenticated {@link User} object.
     * Authenticated, required scope: {@link Scopes#USER_READ}
     *
     * @param handler the response handler
     */
    public void get(final UserResponseHandler handler) {
        String url = String.format("%s/user", getBaseUrl());

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    User value = objectMapper.readValue(content, User.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns the channel subscription that the user subscribes to.
     * Authenticated, required scope: {@link Scopes#USER_SUBSCRIPTIONS}
     *
     * @param user    the authenticated user's name
     * @param channel the channel name of the subscription
     * @param handler the response handler
     */
    public void getSubscription(final String user, final String channel, final UserSubscriptionResponseHandler handler) {
        String url = String.format("%s/users/%s/subscriptions/%s", getBaseUrl(), user, channel);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UserSubscription value = objectMapper.readValue(content, UserSubscription.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a {@link UserFollows} object that contains a list of {@link UserFollow}
     * objects representing channels the user is following.
     *
     * @param user    the user's name
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                <li><code>direction</code>: Sorting direction. Default is <code>desc</code>.
     *                Valid values are <code>asc</code> and <code>desc</code>.</li>
     *                <li><code>sortby</code>: Sort key. Default is <code>created_at</code>.
     *                Valid values are <code>created_at</code> and <code>last_broadcast</code>.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void getFollows(final String user, final RequestParams params, final UserFollowsResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels", getBaseUrl(), user);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UserFollows value = objectMapper.readValue(content, UserFollows.class);
                    handler.onSuccess(value.getTotal(), value.getFollows());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a {@link UserFollows} object that contains a list of {@link UserFollow}
     * objects representing channels the user is following.
     *
     * @param user    the user's name
     * @param handler the response handler
     */
    public void getFollows(final String user, final UserFollowsResponseHandler handler) {
        getFollows(user, new RequestParams(), handler);
    }

    /**
     * Returns a {@link UserFollow} object representing a channel follow.
     *
     * @param user    the user
     * @param channel the channel
     * @param handler the response handler
     */
    public void getFollow(final String user, final String channel, final UserFollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s", getBaseUrl(), user, channel);

        http.get(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UserFollow value = objectMapper.readValue(content, UserFollow.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user                the authenticated user
     * @param channel             the channel to follow
     * @param enableNotifications receive email/push notifications when channel goes live. Default is <code>false</code>.
     * @param handler             the response handler
     */
    public void follow(final String user, final String channel, final boolean enableNotifications, final UserFollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s", getBaseUrl(), user, channel);

        RequestParams params = new RequestParams();
        params.put("notifications", Boolean.toString(enableNotifications));

        http.put(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    UserFollow value = objectMapper.readValue(content, UserFollow.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param channel the channel to follow
     * @param handler the response handler
     */
    public void follow(final String user, final String channel, final UserFollowResponseHandler handler) {
        follow(user, channel, false, handler);
    }

    /**
     * Unfollow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param channel the channel to unfollow
     * @param handler the response handler
     */
    public void unfollow(final String user, final String channel, final UserUnfollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s", getBaseUrl(), user, channel);

        http.delete(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                handler.onSuccess();
            }
        });
    }

    /**
     * Returns a list of {@link Block} objects on <code>User</code>'s block list.
     * List sorted by recency, newest first.
     * Authenticated, required scope: {@link Scopes#USER_BLOCKS_READ}
     *
     * @param user    the authenticated user
     * @param params  the optional request parameters:
     *                <ul>
     *                <li><code>limit</code>:  Maximum number of objects in array. Default is 25. Maximum is 100.</li>
     *                <li><code>offset</code>: Object offset for pagination. Default is 0.</li>
     *                </ul>
     * @param handler the response handler
     */
    public void getBlocks(final String user, final RequestParams params, final BlocksResponseHandler handler) {
        String url = String.format("%s/users/%s/blocks", getBaseUrl(), user);

        http.get(url, params, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Blocks value = objectMapper.readValue(content, Blocks.class);
                    handler.onSuccess(value.getBlocks());
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Returns a list of {@link Block} objects on <code>User</code>'s block list.
     * List sorted by recency, newest first.
     * Authenticated, required scope: {@link Scopes#USER_BLOCKS_READ}
     *
     * @param user    the authenticated user
     * @param handler the response handler
     */
    public void getBlocks(final String user, final BlocksResponseHandler handler) {
        getBlocks(user, new RequestParams(), handler);
    }

    /**
     * Blocks a <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param target  the user to block
     * @param handler the response handler
     */
    public void putBlock(final String user, final String target, final BlockResponseHandler handler) {
        String url = String.format("%s/users/%s/blocks/%s", getBaseUrl(), user, target);

        http.put(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                try {
                    Block value = objectMapper.readValue(content, Block.class);
                    handler.onSuccess(value);
                } catch (IOException e) {
                    handler.onFailure(e);
                }
            }
        });
    }

    /**
     * Removes the {@link Block} of <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param target  the user to unblock
     * @param handler the response handler
     */
    public void deleteBlock(final String user, final String target, final UnblockResponseHandler handler) {
        String url = String.format("%s/users/%s/blocks/%s", getBaseUrl(), user, target);

        http.delete(url, new TwitchHttpResponseHandler(handler) {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                handler.onSuccess();
            }
        });
    }
}
