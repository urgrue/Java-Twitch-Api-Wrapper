package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Ingest;

import java.util.List;

public interface IngestsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Ingest> ingests);
}
