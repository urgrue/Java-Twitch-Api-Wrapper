package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.FeaturedStream;

import java.io.IOException;
import java.util.List;

public interface FeaturedStreamResponseHandler {
    void onSuccess(List<FeaturedStream> streams);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
