package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Stream;

import java.util.List;

public interface StreamsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Stream> streams);
}
