package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.StreamsSummary;
import main.java.com.mb3364.twitch.api.models.StreamsSummary;

import java.io.IOException;

public interface StreamsSummaryResponseHandler {
    void onSuccess(StreamsSummary summary);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
