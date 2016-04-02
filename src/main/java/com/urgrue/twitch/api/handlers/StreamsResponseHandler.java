package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Stream;

import java.util.List;

public interface StreamsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Stream> streams);
}
