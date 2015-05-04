package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Block;

import java.io.IOException;

public interface BlockResponseHandler {
    void onSuccess(Block block);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
