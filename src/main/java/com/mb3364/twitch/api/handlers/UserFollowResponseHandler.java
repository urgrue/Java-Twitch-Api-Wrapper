package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.UserFollow;

import java.io.IOException;

public interface UserFollowResponseHandler {
    void onSuccess(UserFollow follow);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
