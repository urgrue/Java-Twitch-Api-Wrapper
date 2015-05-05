package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelFollow;

import java.util.List;

public interface ChannelFollowsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<ChannelFollow> follows);
}
