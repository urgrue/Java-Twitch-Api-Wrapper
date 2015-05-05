package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelBadges;

public interface BadgesResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelBadges badges);
}
