package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Block;

import java.util.List;

public interface BlocksResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Block> blocks);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
