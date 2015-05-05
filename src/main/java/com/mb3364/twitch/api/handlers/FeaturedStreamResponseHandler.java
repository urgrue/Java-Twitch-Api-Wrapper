package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.FeaturedStream;

import java.util.List;

public interface FeaturedStreamResponseHandler extends BaseFailureHandler {
    void onSuccess(List<FeaturedStream> streams);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
