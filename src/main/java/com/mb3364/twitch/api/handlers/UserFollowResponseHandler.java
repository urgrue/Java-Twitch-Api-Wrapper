package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserFollow;

public interface UserFollowResponseHandler extends BaseFailureHandler {
    void onSuccess(UserFollow follow);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
