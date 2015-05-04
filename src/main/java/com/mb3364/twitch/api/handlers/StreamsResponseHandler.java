package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Stream;
import main.java.com.mb3364.twitch.api.models.Stream;

import java.io.IOException;
import java.util.List;

public interface StreamsResponseHandler {
    void onSuccess(int total, List<Stream> streams);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
