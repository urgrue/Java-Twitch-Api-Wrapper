package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Ingest;

import java.util.List;

public interface IngestsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Ingest> ingests);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
