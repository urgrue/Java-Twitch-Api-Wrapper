package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Video;

public interface VideoResponseHandler extends BaseFailureHandler {
    void onSuccess(Video video);
}
