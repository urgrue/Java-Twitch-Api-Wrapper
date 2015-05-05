package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelBadges;

public interface BadgesResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelBadges badges);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
