package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.FeaturedStream;

import java.util.List;

public interface FeaturedStreamResponseHandler extends BaseFailureHandler {
    void onSuccess(List<FeaturedStream> streams);
}
