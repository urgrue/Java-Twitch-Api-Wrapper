package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Video;

public interface VideoResponseHandler extends BaseFailureHandler {
    void onSuccess(Video video);
}
