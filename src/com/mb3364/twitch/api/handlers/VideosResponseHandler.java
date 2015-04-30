package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Video;

import java.io.IOException;
import java.util.List;

public interface VideosResponseHandler {
    void onSuccess(int total, List<Video> videos);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
