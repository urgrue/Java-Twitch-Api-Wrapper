package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.TopGame;

import java.util.List;

public interface TopGamesResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<TopGame> games);
}
