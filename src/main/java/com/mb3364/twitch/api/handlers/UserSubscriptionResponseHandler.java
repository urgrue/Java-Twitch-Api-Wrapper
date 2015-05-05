package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.UserSubscription;

public interface UserSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(UserSubscription subscription);
}
