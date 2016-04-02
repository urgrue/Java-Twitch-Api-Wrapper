package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.ChannelBadges;

public interface BadgesResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelBadges badges);
}
