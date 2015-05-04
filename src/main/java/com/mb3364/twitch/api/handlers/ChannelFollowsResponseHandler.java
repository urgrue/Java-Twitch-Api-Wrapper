package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelFollow;

import java.io.IOException;
import java.util.List;

public interface ChannelFollowsResponseHandler {
    void onSuccess(int total, List<ChannelFollow> follows);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
