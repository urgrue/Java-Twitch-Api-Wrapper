package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersResponseHandler {
    void onSuccess(List<User> users);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
