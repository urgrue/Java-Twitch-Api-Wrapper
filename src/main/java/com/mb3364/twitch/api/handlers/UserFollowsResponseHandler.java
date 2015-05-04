package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.UserFollow;

import java.io.IOException;
import java.util.List;

public interface UserFollowsResponseHandler {
    void onSuccess(int total, List<UserFollow> follows);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
