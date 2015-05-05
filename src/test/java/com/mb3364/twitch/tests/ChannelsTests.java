package com.mb3364.twitch.tests;


import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.*;
import com.mb3364.twitch.api.models.*;
import com.mb3364.twitch.http.JsonParams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ChannelsTests {

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
    public void getTestAuthed() {
        client.channels().get(new ChannelResponseHandler() {
            @Override
            public void onSuccess(Channel channel) {
                System.out.println("Success");
                System.out.println(channel);
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
    public void getTest() {
        client.channels().get("lirik", new ChannelResponseHandler() {
            @Override
            public void onSuccess(Channel channel) {
                System.out.println("Success");
                System.out.println(channel);
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
    public void getEditorsTest() {
        client.channels().getEditors(AUTH_USER, new UsersResponseHandler() {
            @Override
            public void onSuccess(List<User> users) {
                System.out.println("Success");
                System.out.println(users);
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
    public void putTest() {
        JsonParams params = new JsonParams();
        params.put("status", "new status");
        params.put("game", "Mortal Kombat");

        client.channels().put(AUTH_USER, params, new ChannelResponseHandler() {
            @Override
            public void onSuccess(Channel channel) {
                System.out.println("Success");
                System.out.println(channel);
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
    public void resetStreamKeyTest() {
        client.channels().resetStreamKey(AUTH_USER, new ChannelResponseHandler() {
            @Override
            public void onSuccess(Channel channel) {
                System.out.println("Success");
                System.out.println(channel);
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
    public void startCommercialTest() {
        client.channels().startCommercial(AUTH_USER, 30, new CommercialResponseHandler() {
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
    public void getTeams() {
        client.channels().getTeams("lirik", new TeamsResponseHandler() {
            @Override
            public void onSuccess(List<Team> teams) {
                System.out.println("Success");
                System.out.println(teams);
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
    public void getFollows() {
        client.channels().getFollows(AUTH_USER, new ChannelFollowsResponseHandler() {
            @Override
            public void onSuccess(int total, List<ChannelFollow> follows) {
                System.out.println("Success");
                System.out.println("Total: " + total);
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
    public void getVideos() {
        JsonParams params = new JsonParams();
        params.put("limit", 10);
        params.put("broadcasts", true);

        client.channels().getVideos("lirik", params, new VideosResponseHandler() {
            @Override
            public void onSuccess(int total, List<Video> videos) {
                System.out.println("Success");
                System.out.println("Total: " + total);
                System.out.println("Current: " + videos.size());
                System.out.println(videos);
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
    public void getSubscriptionsTest() {
        JsonParams params = new JsonParams();
        params.put("limit", 2);

        client.channels().getSubscriptions(AUTH_USER, params, new ChannelSubscriptionsResponseHandler() {
            @Override
            public void onSuccess(int total, List<ChannelSubscription> subscriptions) {
                System.out.println("Success");
                System.out.println("Total: " + total);
                System.out.println("Current: " + subscriptions.size());
                System.out.println(subscriptions);
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
        client.channels().getSubscription(AUTH_USER, "lirik", new ChannelSubscriptionResponseHandler() {
            @Override
            public void onSuccess(ChannelSubscription subscription) {
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
}
