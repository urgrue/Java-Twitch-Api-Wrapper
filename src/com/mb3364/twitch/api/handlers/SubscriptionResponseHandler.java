package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelSubscription;

import java.io.IOException;

public interface SubscriptionResponseHandler {
    void onSuccess(ChannelSubscription subscription);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
