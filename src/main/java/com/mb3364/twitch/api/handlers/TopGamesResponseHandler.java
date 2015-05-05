package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.TopGame;

import java.util.List;

public interface TopGamesResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<TopGame> games);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
