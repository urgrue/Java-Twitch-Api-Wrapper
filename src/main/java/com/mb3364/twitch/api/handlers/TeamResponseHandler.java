package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Team;
import main.java.com.mb3364.twitch.api.models.Team;

import java.io.IOException;

public interface TeamResponseHandler {
    void onSuccess(Team team);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
