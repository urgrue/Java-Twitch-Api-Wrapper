package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Team;

import java.io.IOException;
import java.util.List;

public interface TeamsResponseHandler {
    void onSuccess(List<Team> teams);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
