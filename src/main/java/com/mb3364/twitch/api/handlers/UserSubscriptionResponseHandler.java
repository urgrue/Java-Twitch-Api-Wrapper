package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserSubscription;

import java.io.IOException;

public interface UserSubscriptionResponseHandler {
    void onSuccess(UserSubscription subscription);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
