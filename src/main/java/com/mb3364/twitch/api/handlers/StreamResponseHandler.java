package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Stream;
import main.java.com.mb3364.twitch.api.models.Stream;

import java.io.IOException;

public interface StreamResponseHandler {
    /**
     * API callback was successful.
     *
     * @param stream the stream object. Will be <code>null</code> if the stream is offline!
     */
    void onSuccess(Stream stream);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
