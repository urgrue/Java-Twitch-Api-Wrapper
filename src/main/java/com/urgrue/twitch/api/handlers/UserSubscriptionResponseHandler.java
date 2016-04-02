package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.UserSubscription;

public interface UserSubscriptionResponseHandler extends BaseFailureHandler {
    void onSuccess(UserSubscription subscription);
}
