package com.mb3364.twitch.api.resources;

import com.mb3364.twitch.api.TwitchResponse;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.http.HttpResponse;

import java.io.IOException;
import java.util.List;

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
     * @param user the user to request
     * @return a TwitchResponse containing a {@link User} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<User> get(String user) throws IOException {
        String url = String.format("%s/users/%s", getBaseUrl(), user);
        return requestGet(url, HttpResponse.HTTP_OK, User.class);
    }

    /**
     * Returns the authenticated {@link User} object.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_READ}
     *
     * @return a TwitchResponse containing a {@link User} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<User> get() throws IOException {
        String url = String.format("%s/user", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, User.class);
    }

    /**
     * Returns a channel object that the authenticated user subscribes to.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_SUBSCRIPTIONS}
     *
     * @param user    the authenticated user's name
     * @param channel the channel name of the subscription
     * @return a TwitchResponse containing a {@link UserSubscription} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<UserSubscription> getSubscription(String user, String channel) throws IOException {
        String url = String.format("%s/users/%s/subscriptions/%s",
                getBaseUrl(), user, channel);

        return requestGet(url, HttpResponse.HTTP_OK, UserSubscription.class);
    }

    /**
     * Returns a {@link UserFollows} object that contains a list of {@link UserFollow}
     * objects representing channels the user is following.
     *
     * @param user      the user
     * @param limit     the maximum number of objects in array. Maximum is 100
     * @param offset    the object offset for pagination
     * @param direction the sorting direction. Valid values are <code>asc</code> and <code>desc</code>
     * @param sortby    the sort key. Valid values are <code>created_at</code> and <code>last_broadcast</code>
     * @return a TwitchResponse containing a {@link UserFollows} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<UserFollows> getFollows(String user, int limit, int offset, String direction, String sortby) throws IOException {
        String url = String.format("%s/users/%s/follows/channels?limit=%s&offset=%s&direction=%s&sortby=%s",
                getBaseUrl(), user, limit, offset, direction, sortby);

        return requestGet(url, HttpResponse.HTTP_OK, UserFollows.class);
    }

    /**
     * Returns a {@link UserFollow} object representing a channel follow.
     *
     * @param user    the user
     * @param channel the channel
     * @return a TwitchResponse containing a {@link UserFollow} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<UserFollow> getFollow(String user, String channel) throws IOException {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);

        return requestGet(url, HttpResponse.HTTP_OK, UserFollow.class);
    }

    /**
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user                the authenticated user
     * @param channel             the channel to follow
     * @param enableNotifications receive email/push notifications when channel goes live. Default is false.
     * @return a TwitchResponse containing the {@link UserFollow} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<UserFollow> follow(String user, String channel, boolean enableNotifications) throws IOException {
        String url = String.format("%s/users/%s/follows/channels/%s?notifications=%s",
                getBaseUrl(), user, channel, enableNotifications);

        return requestPut(url, HttpResponse.HTTP_OK, UserFollow.class);
    }

    /**
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param channel the channel to follow
     * @return a TwitchResponse containing the {@link UserFollow} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<UserFollow> follow(String user, String channel) throws IOException {
        return follow(user, channel, false);
    }

    /**
     * Unfollow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param channel the channel to unfollow
     * @return a TwitchResponse containing a {@link Empty} object as no data is returned
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Empty> unfollow(String user, String channel) throws IOException {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);

        return requestPut(url, HttpResponse.HTTP_OK, Empty.class);
    }

    /**
     * Returns a list of {@link Block} objects on <code>User</code>'s block list.
     * List sorted by recency, newest first.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_BLOCKS_READ}
     *
     * @param user   the authenticated user
     * @param limit  the maximum number of block's to return
     * @param offset the object offset for pagination
     * @return a TwitchResponse containing a list of {@link Block}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Block>> getBlocks(String user, int limit, int offset) throws IOException {
        // Constrain limit
        limit = Math.max(limit, 1);
        limit = Math.min(limit, 100);

        String url = String.format("%s/users/%s/blocks?limit=%s&offset=%s",
                getBaseUrl(), user, limit, offset);

        TwitchResponse<Blocks> container = requestGet(url, HttpResponse.HTTP_OK, Blocks.class);

        // Create object with list rather than the container class
        TwitchResponse<List<Block>> response = new TwitchResponse<List<Block>>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getBlocks());
        }

        return response;
    }

    /**
     * Returns a list of {@link Block} objects on <code>User</code>'s block list.
     * List sorted by recency, newest first.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_BLOCKS_READ}
     *
     * @param user the authenticated user
     * @return a TwitchResponse containing a list of {@link Block}'s
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<List<Block>> getBlocks(String user) throws IOException {
        return getBlocks(user, 25, 0);
    }

    /**
     * Blocks a <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user   the authenticated user
     * @param target the user to block
     * @return a TwitchResponse containing the {@link Block} object
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Block> addBlock(String user, String target) throws IOException {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestGet(url, HttpResponse.HTTP_OK, Block.class);
    }

    /**
     * Removes the {@link Block} of <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link com.mb3364.twitch.api.auth.Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user   the authenticated user
     * @param target the user to unblock
     * @return a TwitchResponse containing an {@link Empty} object since the block was removed
     * @throws IOException if an error occurs during the request
     */
    public TwitchResponse<Empty> deleteBlock(String user, String target) throws IOException {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestPut(url, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

}
