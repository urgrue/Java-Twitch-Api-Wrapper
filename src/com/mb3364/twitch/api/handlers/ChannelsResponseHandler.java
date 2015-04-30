package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Channel;

import java.io.IOException;
import java.util.List;

public interface ChannelsResponseHandler {
    void onSuccess(int total, List<Channel> channels);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
