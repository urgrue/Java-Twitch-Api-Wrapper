package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.ChannelFollow;

import java.util.List;

public interface ChannelFollowsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<ChannelFollow> follows);
}
