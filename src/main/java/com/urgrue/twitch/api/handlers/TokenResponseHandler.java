package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Token;

public interface TokenResponseHandler extends BaseFailureHandler {
    void onSuccess(Token token);
}
