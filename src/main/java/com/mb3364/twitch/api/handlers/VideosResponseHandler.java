package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Video;

import java.util.List;

public interface VideosResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Video> videos);
}
