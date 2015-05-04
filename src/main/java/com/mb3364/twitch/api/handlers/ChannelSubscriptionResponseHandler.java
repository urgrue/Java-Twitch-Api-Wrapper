package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.ChannelSubscription;
import main.java.com.mb3364.twitch.api.models.ChannelSubscription;

import java.io.IOException;

public interface ChannelSubscriptionResponseHandler {
    void onSuccess(ChannelSubscription subscription);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
