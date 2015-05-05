package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Team;

import java.util.List;

public interface TeamsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Team> teams);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
