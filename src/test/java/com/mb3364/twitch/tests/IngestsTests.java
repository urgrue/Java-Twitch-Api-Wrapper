package com.mb3364.twitch.tests;

import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.IngestsResponseHandler;
import com.mb3364.twitch.api.models.Ingest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IngestsTests {
    private final static String CLIENT_ID = "3ecse7kg5j1tmagtkmzzyxqmvtw1lze";

    private Twitch client;

    @Before
    public void before() {
        client = new Twitch();
        client.setClientId(CLIENT_ID);
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void getTest() throws IOException {
        client.ingests().get(new IngestsResponseHandler() {
            @Override
            public void onSuccess(List<Ingest> ingests) {
                System.out.println("Success");
                System.out.println(ingests);
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
