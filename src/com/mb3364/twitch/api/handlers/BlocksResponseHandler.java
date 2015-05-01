package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Block;

import java.io.IOException;
import java.util.List;

public interface BlocksResponseHandler {
    void onSuccess(List<Block> blocks);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
