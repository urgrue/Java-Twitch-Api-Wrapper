package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Channel;

import java.util.List;

public interface ChannelsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Channel> channels);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
