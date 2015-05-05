package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Emoticon;

import java.util.List;

public interface EmoticonsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Emoticon> emoticons);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
