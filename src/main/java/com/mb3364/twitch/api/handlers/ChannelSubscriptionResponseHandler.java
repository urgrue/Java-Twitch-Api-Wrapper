package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelSubscription;

public interface ChannelSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelSubscription subscription);
}
