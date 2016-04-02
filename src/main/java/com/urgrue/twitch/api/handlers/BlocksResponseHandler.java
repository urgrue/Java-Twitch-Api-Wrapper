package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Block;

import java.util.List;

public interface BlocksResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Block> blocks);
}
