package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserFollow;

import java.util.List;

public interface UserFollowsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<UserFollow> follows);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
