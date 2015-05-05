package com.mb3364.twitch.api.handlers;

public interface UnblockResponseHandler extends BaseFailureHandler {
    void onSuccess();

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
