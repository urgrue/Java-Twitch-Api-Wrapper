package com.mb3364.twitch;

import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.auth.Scopes;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AuthTests {

    private final static String CLIENT_ID = "3ecse7kg5j1tmagtkmzzyxqmvtw1lze";
    private final static String CALLBACK = "http://127.0.0.1:23522/authorize.html";

    private static URI callbackUri;

    @Before
    public void init() {
        try {
            URL url = new URL(CALLBACK);
            callbackUri = url.toURI();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAuthUrl() {
        Twitch twitch = new Twitch();
        String authUrl = twitch.auth().getAuthenticationUrl(CLIENT_ID, callbackUri, Scopes.class.getEnumConstants());
        System.out.println(authUrl);
    }

    @Test
    public void testAuth() throws URISyntaxException {
        Twitch twitch = new Twitch();
        String authUrl = twitch.auth().getAuthenticationUrl(CLIENT_ID, callbackUri, Scopes.class.getEnumConstants());
        openWebpage(authUrl);

        boolean success = twitch.auth().awaitAccessToken();
        if (success) {
            String accessToken = twitch.auth().getAccessToken();
            System.out.println("Access Token: " + accessToken);
        } else {
            System.out.println(twitch.auth().getAuthenticationError());
        }
    }

//    @Test
//    public void explicitTest() throws IOException {
//        String authToken = "9z2ompq3y9zwx58emz6u9w86mn477s";
//        Twitch twitch = new Twitch();
//        twitch.setClientId(CLIENT_ID);
//        twitch.auth().setAccessToken(authToken);
//
//        TwitchResponse<Channel> mine = twitch.channels().get();
//        if (!mine.hasError()) {
//            System.out.println(mine.getObject());
//        } else {
//            System.out.println(mine.getErrorMessage());
//        }
//    }

    public static void openWebpage(String urlString) {
        try {
            java.awt.Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
