package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Ingest;

import java.io.IOException;
import java.util.List;

public interface IngestsResponseHandler {
    void onSuccess(List<Ingest> ingests);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
