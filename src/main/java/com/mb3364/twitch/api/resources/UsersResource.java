package main.java.com.mb3364.twitch.api.resources;

import main.java.com.mb3364.twitch.http.HttpClient;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.http.JsonParams;
import main.java.com.mb3364.twitch.api.auth.Scopes;
import main.java.com.mb3364.twitch.api.handlers.*;
import main.java.com.mb3364.twitch.api.models.*;
import main.java.com.mb3364.twitch.api.models.Error;
import main.java.com.mb3364.twitch.http.HttpClient;
import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.http.JsonParams;

import java.io.IOException;

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
    public void get(String user, UserResponseHandler handler) {
        String url = String.format("%s/users/%s", getBaseUrl(), user);
        try {

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                User value = objectMapper.readValue(response.getContent(), User.class);
                handler.onSuccess(value);
            } else {
                main.java.com.mb3364.twitch.api.models.Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns the authenticated {@link User} object.
     * Authenticated, required scope: {@link Scopes#USER_READ}
     *
     * @param handler the response handler
     */
    public void get(UserResponseHandler handler) {
        String url = String.format("%s/user", getBaseUrl());
        try {

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                User value = objectMapper.readValue(response.getContent(), User.class);
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
     * Returns the channel subscription that the user subscribes to.
     * Authenticated, required scope: {@link Scopes#USER_SUBSCRIPTIONS}
     *
     * @param user    the authenticated user's name
     * @param channel the channel name of the subscription
     * @param handler the response handler
     */
    public void getSubscription(String user, String channel, UserSubscriptionResponseHandler handler) {
        String url = String.format("%s/users/%s/subscriptions/%s",
                getBaseUrl(), user, channel);
        try {

            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                UserSubscription value = objectMapper.readValue(response.getContent(), UserSubscription.class);
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
    public void getFollows(String user, JsonParams params, UserFollowsResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/users/%s/follows/channels?%s",
                getBaseUrl(), user, params.toQueryString());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                UserFollows value = objectMapper.readValue(response.getContent(), UserFollows.class);
                handler.onSuccess(value.getTotal(), value.getFollows());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a {@link UserFollows} object that contains a list of {@link UserFollow}
     * objects representing channels the user is following.
     *
     * @param user    the user's name
     * @param handler the response handler
     */
    public void getFollows(String user, UserFollowsResponseHandler handler) {
        getFollows(user, null, handler);
    }

    /**
     * Returns a {@link UserFollow} object representing a channel follow.
     *
     * @param user    the user
     * @param channel the channel
     * @param handler the response handler
     */
    public void getFollow(String user, String channel, UserFollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                UserFollow value = objectMapper.readValue(response.getContent(), UserFollow.class);
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
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user                the authenticated user
     * @param channel             the channel to follow
     * @param enableNotifications receive email/push notifications when channel goes live. Default is <code>false</code>.
     * @param handler             the response handler
     */
    public void follow(String user, String channel, boolean enableNotifications, UserFollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s?notifications=%s",
                getBaseUrl(), user, channel, enableNotifications);

        System.out.println(url);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.put(url, headers);

            int statusCode = response.getCode();

            if (statusCode == HttpResponse.HTTP_OK) {
                UserFollow value = objectMapper.readValue(response.getContent(), UserFollow.class);
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
     * Follow a channel. Must be authenticated as the <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param channel the channel to follow
     * @param handler the response handler
     */
    public void follow(String user, String channel, UserFollowResponseHandler handler) {
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
    public void unfollow(String user, String channel, UserUnfollowResponseHandler handler) {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.delete(url, headers);

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
    public void getBlocks(String user, JsonParams params, BlocksResponseHandler handler) {
        if (params == null) params = new JsonParams();
        String url = String.format("%s/users/%s/blocks?%s", getBaseUrl(), user, params.toQueryString());

        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.get(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Blocks value = objectMapper.readValue(response.getContent(), Blocks.class);
                handler.onSuccess(value.getBlocks());
            } else {
                Error error = objectMapper.readValue(response.getContent(), Error.class);
                handler.onFailure(error.getStatusCode(), error.getStatusText(), error.getMessage());
            }
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Returns a list of {@link Block} objects on <code>User</code>'s block list.
     * List sorted by recency, newest first.
     * Authenticated, required scope: {@link Scopes#USER_BLOCKS_READ}
     *
     * @param user    the authenticated user
     * @param handler the response handler
     */
    public void getBlocks(String user, BlocksResponseHandler handler) {
        getBlocks(user, null, handler);
    }

    /**
     * Blocks a <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param target  the user to block
     * @param handler the response handler
     */
    public void putBlock(String user, String target, BlockResponseHandler handler) {
        String url = String.format("%s/users/%s/blocks/%s", getBaseUrl(), user, target);
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.put(url, headers);

            int statusCode = response.getCode();
            if (statusCode == HttpResponse.HTTP_OK) {
                Block value = objectMapper.readValue(response.getContent(), Block.class);
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
     * Removes the {@link Block} of <code>target</code> for the authenticated <code>user</code>.
     * Authenticated, required scope: {@link Scopes#USER_FOLLOWS_EDIT}
     *
     * @param user    the authenticated user
     * @param target  the user to unblock
     * @param handler the response handler
     */
    public void deleteBlock(String user, String target, UnblockResponseHandler handler) {
        String url = String.format("%s/users/%s/blocks/%s", getBaseUrl(), user, target);
        try {
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.delete(url, headers);

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
}
