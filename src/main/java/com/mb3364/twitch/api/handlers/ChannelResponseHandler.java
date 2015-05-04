package main.java.com.mb3364.twitch.api.handlers;

import main.java.com.mb3364.twitch.api.models.Channel;

import java.io.IOException;

public interface ChannelResponseHandler {
    void onSuccess(Channel channel);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
