package main.java.com.mb3364.twitch.api.handlers;

import java.io.IOException;

public interface UserUnfollowResponseHandler {
    void onSuccess();

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
