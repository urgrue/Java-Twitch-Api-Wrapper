package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserFollow;

import java.util.List;

public interface UserFollowsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<UserFollow> follows);
}
