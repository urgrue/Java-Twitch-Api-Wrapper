package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Channel;

import java.util.List;

public interface ChannelsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<Channel> channels);
}
