package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.ChannelSubscription;

import java.io.IOException;
import java.util.List;

public interface ChannelSubscriptionsResponseHandler {
    void onSuccess(int total, List<ChannelSubscription> subscriptions);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
