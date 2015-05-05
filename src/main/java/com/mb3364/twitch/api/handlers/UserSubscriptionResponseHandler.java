package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserSubscription;

public interface UserSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(UserSubscription subscription);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
