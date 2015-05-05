package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Token;

public interface TokenResponseHandler extends BaseFailureHandler {
    void onSuccess(Token token);
}
