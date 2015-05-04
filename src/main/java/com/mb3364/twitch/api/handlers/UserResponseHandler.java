package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.User;
import main.java.com.mb3364.twitch.api.models.User;

import java.io.IOException;

public interface UserResponseHandler {
    void onSuccess(User user);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
