package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Block;

public interface BlockResponseHandler extends BaseFailureHandler {
    void onSuccess(Block block);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
