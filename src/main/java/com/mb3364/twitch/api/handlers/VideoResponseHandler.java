package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Video;

public interface VideoResponseHandler extends BaseFailureHandler {
    void onSuccess(Video video);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
