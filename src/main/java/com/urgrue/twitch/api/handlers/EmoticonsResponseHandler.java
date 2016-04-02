package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Emoticon;

import java.util.List;

public interface EmoticonsResponseHandler extends BaseFailureHandler {
    void onSuccess(List<Emoticon> emoticons);
}
