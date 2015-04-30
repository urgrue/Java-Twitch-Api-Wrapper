package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.ChannelBadges;

import java.io.IOException;

public interface BadgesResponseHandler {
    void onSuccess(ChannelBadges badges);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
