package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.User;

import java.util.List;

public interface UsersResponseHandler extends BaseFailureHandler {
    void onSuccess(List<User> users);
}
