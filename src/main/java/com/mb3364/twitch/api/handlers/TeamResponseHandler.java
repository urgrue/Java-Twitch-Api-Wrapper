package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Team;

public interface TeamResponseHandler extends BaseFailureHandler {
    void onSuccess(Team team);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
