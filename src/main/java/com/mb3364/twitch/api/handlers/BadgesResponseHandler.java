package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.ChannelBadges;

import java.io.IOException;

public interface BadgesResponseHandler {
    void onSuccess(ChannelBadges badges);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
