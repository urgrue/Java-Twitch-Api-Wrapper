package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Game;

import java.io.IOException;
import java.util.List;

public interface GamesResponseHandler {
    void onSuccess(int total, List<Game> streams);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
