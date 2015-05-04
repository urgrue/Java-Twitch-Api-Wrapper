package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.TopGame;
import main.java.com.mb3364.twitch.api.models.TopGame;

import java.io.IOException;
import java.util.List;

public interface TopGamesResponseHandler {
    void onSuccess(int total, List<TopGame> games);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
