package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Team;

import java.io.IOException;

public interface TeamResponseHandler {
    void onSuccess(Team team);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
