package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersResponseHandler {
    void onSuccess(List<User> users);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
