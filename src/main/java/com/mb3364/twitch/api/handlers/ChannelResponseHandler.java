package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Channel;

public interface ChannelResponseHandler extends BaseFailureHandler {
    void onSuccess(Channel channel);
}
