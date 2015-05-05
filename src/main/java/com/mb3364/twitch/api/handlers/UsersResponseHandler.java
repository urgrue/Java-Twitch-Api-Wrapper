package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.User;

import java.util.List;

public interface UsersResponseHandler extends BaseFailureHandler {
    void onSuccess(List<User> users);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
