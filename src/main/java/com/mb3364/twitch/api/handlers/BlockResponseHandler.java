package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Block;

public interface BlockResponseHandler extends BaseFailureHandler {
    void onSuccess(Block block);
}
