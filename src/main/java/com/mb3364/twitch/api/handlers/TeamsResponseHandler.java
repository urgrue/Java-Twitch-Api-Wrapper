package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Team;
import main.java.com.mb3364.twitch.api.models.Team;

import java.io.IOException;
import java.util.List;

public interface TeamsResponseHandler {
    void onSuccess(List<Team> teams);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
