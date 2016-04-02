package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Channel;

public interface ChannelResponseHandler extends BaseFailureHandler {
    void onSuccess(Channel channel);
}
