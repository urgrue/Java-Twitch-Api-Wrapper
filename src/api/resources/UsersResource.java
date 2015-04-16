package api.resources;

import api.models.Empty;
import api.TwitchResponse;
import api.models.Block;
import api.models.Blocks;
import api.models.UserFollow;
import api.models.UserFollows;
import api.models.UserSubscription;
import api.models.User;
import http.HttpResponse;

import java.util.List;

public class UsersResource extends TwitchResource {
    public UsersResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<User> get(String user) {
        String url = String.format("%s/users/%s", getBaseUrl(), user);
        return requestGet(url, HttpResponse.HTTP_OK, User.class);
    }

    public TwitchResponse<User> get() {
        String url = String.format("%s/user", getBaseUrl());
        return requestGet(url, HttpResponse.HTTP_OK, User.class);
    }

    public TwitchResponse<UserSubscription> getSubscription(String user, String channel) {
        String url = String.format("%s/users/%s/subscriptions/%s",
                getBaseUrl(), user, channel);

        return requestGet(url, HttpResponse.HTTP_OK, UserSubscription.class);
    }

    public TwitchResponse<UserFollows> getFollows(String user, int limit, int offset, String direction, String sortby) {
        String url = String.format("%s/users/%s/follows/channels?limit=%s&offset=%s&direction=%s&sortby=%s",
                getBaseUrl(), user, limit, offset, direction, sortby);

        return requestGet(url, HttpResponse.HTTP_OK, UserFollows.class);
    }

    public TwitchResponse<UserFollow> getFollow(String user, String channel) {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);

        return requestGet(url, HttpResponse.HTTP_OK, UserFollow.class);
    }

    public TwitchResponse<UserFollow> follow(String user, String channel, boolean enableNotifications) {
        String url = String.format("%s/users/%s/follows/channels/%s?notifications=%s",
                getBaseUrl(), user, channel, enableNotifications);

        return requestPut(url, HttpResponse.HTTP_OK, UserFollow.class);
    }

    public TwitchResponse<UserFollow> follow(String user, String channel) {
        return follow(user, channel, false);
    }

    public TwitchResponse<Empty> unfollow(String user, String channel) {
        String url = String.format("%s/users/%s/follows/channels/%s",
                getBaseUrl(), user, channel);

        return requestPut(url, HttpResponse.HTTP_OK, Empty.class);
    }

    public TwitchResponse<List<Block>> getBlocks(String user, int limit, int offset) {
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

    public TwitchResponse<List<Block>> getBlocks(String user) {
        return getBlocks(user, 25, 0);
    }

    public TwitchResponse<Block> addBlock(String user, String target) {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestGet(url, HttpResponse.HTTP_OK, Block.class);
    }

    public TwitchResponse<Empty> removeBlock(String user, String target) {
        String url = String.format("%s/users/%s/blocks/%s",
                getBaseUrl(), user, target);

        return requestPut(url, HttpResponse.HTTP_NO_CONTENT, Empty.class);
    }

}
