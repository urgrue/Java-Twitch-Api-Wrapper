package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserFollow;

import java.io.IOException;
import java.util.List;

public interface UserFollowsResponseHandler {
    void onSuccess(int total, List<UserFollow> follows);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
