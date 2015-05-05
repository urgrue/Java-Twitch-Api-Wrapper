package com.mb3364.twitch.tests;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.FeaturedStreamResponseHandler;
import com.mb3364.twitch.api.handlers.StreamResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsResponseHandler;
import com.mb3364.twitch.api.handlers.StreamsSummaryResponseHandler;
import com.mb3364.twitch.api.models.FeaturedStream;
import com.mb3364.twitch.api.models.Stream;
import com.mb3364.twitch.api.models.StreamsSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StreamsTests {

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

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void getTest() {
        client.streams().get(AUTH_USER, new StreamResponseHandler() {
            @Override
            public void onSuccess(Stream stream) {
                System.out.println("Success");
                if (stream == null) {
                    System.out.println("Stream is offline");
                } else {
                    System.out.println(stream);
                }
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getListTest() {
        RequestParams params = new RequestParams();
        params.put("channel", "lirik,summit1g");
        params.put("limit", "2");

        client.streams().get(params, new StreamsResponseHandler() {
            @Override
            public void onSuccess(int total, List<Stream> streams) {
                System.out.println("Success");
                System.out.println(total);
                System.out.println(streams);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getFeaturedTest() {
        RequestParams params = new RequestParams();
        params.put("offset", "10");
        params.put("limit", "2");

        client.streams().getFeatured(params, new FeaturedStreamResponseHandler() {
            @Override
            public void onSuccess(List<FeaturedStream> streams) {
                System.out.println("Success");
                System.out.println("Total: " + streams.size());
                System.out.println(streams);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getSummaryTest() {
        String game = "Rust";
        client.streams().getSummary(game, new StreamsSummaryResponseHandler() {
            @Override
            public void onSuccess(StreamsSummary summary) {
                System.out.println("Success");
                System.out.println(summary);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getFollowedTest() {
        RequestParams params = new RequestParams();
        params.put("offset", "0");
        params.put("limit", "100");

        client.streams().getFollowed(params, new StreamsResponseHandler() {
            @Override
            public void onSuccess(int total, List<Stream> streams) {
                System.out.println("Success");
                System.out.println(total);
                System.out.println(streams);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                System.out.println(statusCode);
                System.out.println(statusMessage);
                System.out.println(errorMessage);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
