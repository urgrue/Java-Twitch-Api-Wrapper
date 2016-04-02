package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.UserFollow;

public interface UserFollowResponseHandler extends BaseFailureHandler {
    void onSuccess(UserFollow follow);
}
