package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.User;

public interface UserResponseHandler extends BaseFailureHandler {
    void onSuccess(User user);
}
