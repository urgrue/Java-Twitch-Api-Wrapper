package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.ChannelSubscription;

public interface ChannelSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(ChannelSubscription subscription);
}
