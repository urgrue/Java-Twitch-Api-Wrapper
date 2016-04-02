package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Block;

public interface BlockResponseHandler extends BaseFailureHandler {
    void onSuccess(Block block);
}
