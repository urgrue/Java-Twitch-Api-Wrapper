package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.ChannelSubscription;

import java.util.List;

public interface ChannelSubscriptionsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<ChannelSubscription> subscriptions);
}
