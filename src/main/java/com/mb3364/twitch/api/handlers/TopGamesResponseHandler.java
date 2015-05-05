package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.TopGame;

import java.util.List;

public interface TopGamesResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<TopGame> games);
}
