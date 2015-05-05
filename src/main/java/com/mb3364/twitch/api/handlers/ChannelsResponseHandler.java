package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Channel;

import java.util.List;

public interface ChannelsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Channel> channels);
}
