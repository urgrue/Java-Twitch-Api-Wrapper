package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.User;

import java.util.List;

public interface UsersResponseHandler extends BaseFailureHandler {
    void onSuccess(List<User> users);
}
