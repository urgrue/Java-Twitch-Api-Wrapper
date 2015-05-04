package com.mb3364.twitch;

import com.mb3364.twitch.api.auth.grants.implicit.AuthenticationCallbackServer;
import com.mb3364.twitch.api.auth.grants.implicit.AuthenticationError;
import org.junit.Test;

import java.io.IOException;

public class WebServerTests {

    @Test
    public void test() {

        // https://api.twitch.tv/kraken/oauth2/authorize?response_type=token&client_id=3ecse7kg5j1tmagtkmzzyxqmvtw1lze&redirect_uri=http://127.0.0.1:23522/authorize.html&scope=user_read
        AuthenticationCallbackServer server = new AuthenticationCallbackServer(23522);

        try {
            server.start();
            if (!server.hasAuthenticationError()) {
                System.out.println("Authentication Success");
                System.out.println("Access Token: " + server.getAccessToken());
            } else {
                AuthenticationError error = server.getAuthenticationError();
                System.out.println("Authentication Failed");
                System.out.println(error.getName() + " : " + error.getDescription());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
