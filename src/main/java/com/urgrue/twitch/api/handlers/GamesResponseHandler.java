package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Game;

import java.util.List;

public interface GamesResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Game> streams);
}
