package com.urgrue.twitch.api.handlers;

public interface BaseFailureHandler {
    public void onFailure(int statusCode, String statusMessage, String errorMessage);

    public void onFailure(Throwable throwable);
}
