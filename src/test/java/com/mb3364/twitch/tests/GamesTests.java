package com.mb3364.twitch.tests;

import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.TopGamesResponseHandler;
import com.mb3364.twitch.api.models.TopGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class GamesTests {

    private final static String CLIENT_ID = "3ecse7kg5j1tmagtkmzzyxqmvtw1lze";

    private Twitch client;

    @Before
    public void before() {
        client = new Twitch();
        client.setClientId(CLIENT_ID);
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2500);
    }

    @Test
    public void getTopWithParamsTest() throws IOException {
        RequestParams params = new RequestParams();
        params.put("limit", "1");
        params.put("offset", "1");

        client.games().getTop(params, new TopGamesResponseHandler() {
            @Override
            public void onSuccess(int total, List<TopGame> games) {
                System.out.println("Total: " + total);
                System.out.println(games);
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
    public void getTopTest() throws IOException {
        client.games().getTop(new TopGamesResponseHandler() {
            @Override
            public void onSuccess(int total, List<TopGame> games) {
                System.out.println("Total: " + total);
                System.out.println(games);
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
