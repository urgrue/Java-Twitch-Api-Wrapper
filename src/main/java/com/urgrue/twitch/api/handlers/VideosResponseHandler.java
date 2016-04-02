package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Video;

import java.util.List;

public interface VideosResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Video> videos);
}
