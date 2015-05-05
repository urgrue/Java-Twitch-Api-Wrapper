package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelSubscription;

import java.util.List;

public interface ChannelSubscriptionsResponseHandler extends BaseFailureHandler {
    void onSuccess(int total, List<ChannelSubscription> subscriptions);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
