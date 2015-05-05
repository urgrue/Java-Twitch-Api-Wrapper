package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserFollow;

public interface UserFollowResponseHandler extends BaseFailureHandler {
    void onSuccess(UserFollow follow);
}
