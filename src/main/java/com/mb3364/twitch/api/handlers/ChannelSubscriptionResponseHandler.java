package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelSubscription;

public interface ChannelSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelSubscription subscription);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
