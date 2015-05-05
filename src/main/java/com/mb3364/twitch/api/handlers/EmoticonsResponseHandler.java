package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Emoticon;

import java.util.List;

public interface EmoticonsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Emoticon> emoticons);
}
