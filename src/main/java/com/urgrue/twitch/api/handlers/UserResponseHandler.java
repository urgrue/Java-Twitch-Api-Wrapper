package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.User;

public interface UserResponseHandler extends BaseFailureHandler {
    void onSuccess(User user);
}
