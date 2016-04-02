package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.UserFollow;

import java.util.List;

public interface UserFollowsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<UserFollow> follows);
}
