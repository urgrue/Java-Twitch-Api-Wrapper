package test.java.com.mb3364.twitch;

import main.java.com.mb3364.twitch.api.Twitch;
import main.java.com.mb3364.twitch.api.handlers.VideoResponseHandler;
import main.java.com.mb3364.twitch.api.handlers.VideosResponseHandler;
import main.java.com.mb3364.twitch.api.models.Video;
import main.java.com.mb3364.twitch.http.JsonParams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class VideosTests {

    private final static String CLIENT_ID = "3ecse7kg5j1tmagtkmzzyxqmvtw1lze";
    private final static String AUTH_TOKEN = "9z2ompq3y9zwx58emz6u9w86mn477s";

    private Twitch client;

    @Before
    public void before() {
        client = new Twitch();
        client.setClientId(CLIENT_ID);
        client.auth().setAccessToken(AUTH_TOKEN);
    }

    @Test
    public void getTest() {
        client.videos().get("c6055863", new VideoResponseHandler() {
            @Override
            public void onSuccess(Video video) {
                System.out.println("Success");
                System.out.println(video);
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
    public void getTopTest() {
        JsonParams params = new JsonParams();
        params.put("limit", 2);

        client.videos().getTop(params, new VideosResponseHandler() {
            @Override
            public void onSuccess(int total, List<Video> videos) {
                System.out.println("Success");
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
    public void getFollowedTest() {
        JsonParams params = new JsonParams();
        params.put("limit", 2);

        client.videos().getFollowed(params, new VideosResponseHandler() {
            @Override
            public void onSuccess(int total, List<Video> videos) {
                System.out.println("Success");
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
}
