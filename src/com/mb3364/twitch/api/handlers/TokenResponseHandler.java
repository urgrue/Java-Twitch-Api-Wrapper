package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.Token;

import java.io.IOException;

public interface TokenResponseHandler {
    void onSuccess(Token token);

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}