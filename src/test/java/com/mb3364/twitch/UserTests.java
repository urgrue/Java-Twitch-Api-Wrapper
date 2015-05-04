package com.mb3364.twitch;

import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.*;
import com.mb3364.twitch.api.models.Block;
import com.mb3364.twitch.api.models.User;
import com.mb3364.twitch.api.models.UserFollow;
import com.mb3364.twitch.api.models.UserSubscription;
import com.mb3364.twitch.http.JsonParams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserTests {

    private final static String CLIENT_ID = "3ecse7kg5j1tmagtkmzzyxqmvtw1lze";
    private final static String AUTH_TOKEN = "9z2ompq3y9zwx58emz6u9w86mn477s";
    private final static String AUTH_USER = "zibzib916";

    private Twitch client;

    @Before
    public void before() {
        client = new Twitch();
        client.setClientId(CLIENT_ID);
        client.auth().setAccessToken(AUTH_TOKEN);
    }

    @Test
    public void getTest() {
        client.users().get("lirik", new UserResponseHandler() {
            @Override
            public void onSuccess(User user) {
                System.out.println("Success");
                System.out.println(user);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getAuthedTest() {
        client.users().get(new UserResponseHandler() {
            @Override
            public void onSuccess(User user) {
                System.out.println("Success");
                System.out.println(user);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getSubscriptionTest() {
        client.users().getSubscription(AUTH_USER, "lirik", new UserSubscriptionResponseHandler() {
            @Override
            public void onSuccess(UserSubscription subscription) {
                System.out.println("Success");
                System.out.println(subscription);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getFollowsTest() {
        JsonParams params = new JsonParams();
        params.put("limit", 2);
        params.put("sortby", "asc");

        client.users().getFollows(AUTH_USER, params, new UserFollowsResponseHandler() {
            @Override
            public void onSuccess(int total, List<UserFollow> follows) {
                System.out.println("Success");
                System.out.println(total);
                System.out.println(follows);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getFollowTest() {
        client.users().getFollow(AUTH_USER, "lirik", new UserFollowResponseHandler() {
            @Override
            public void onSuccess(UserFollow follow) {
                System.out.println("Success");
                System.out.println(follow);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void followTest() {
        client.users().follow(AUTH_USER, "summit1g", new UserFollowResponseHandler() {
            @Override
            public void onSuccess(UserFollow follow) {
                System.out.println("Success");
                System.out.println(follow);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void unfollowTest() {
        client.users().unfollow(AUTH_USER, "summit1g", new UserUnfollowResponseHandler() {
            @Override
            public void onSuccess() {
                System.out.println("Success");
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getBlocksTest() {
        JsonParams params = new JsonParams();
        params.put("limit", 10);
        params.put("offset", 0);

        client.users().getBlocks(AUTH_USER, params, new BlocksResponseHandler() {
            @Override
            public void onSuccess(List<Block> blocks) {
                System.out.println("Success");
                System.out.println("Count: " + blocks.size());
                System.out.println(blocks);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    public void putBlockTest() {
        client.users().putBlock(AUTH_USER, "testuser", new BlockResponseHandler() {
            @Override
            public void onSuccess(Block block) {
                System.out.println("Success");
                System.out.println(block);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void deleteBlockTest() {
        client.users().deleteBlock(AUTH_USER, "testuser", new UnblockResponseHandler() {
            @Override
            public void onSuccess() {
                System.out.println("Success");
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
            }
        });
    }
}
