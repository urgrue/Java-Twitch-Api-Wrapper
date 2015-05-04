package com.mb3364.twitch.api.handlers;

import java.io.IOException;

public interface CommercialResponseHandler {
    void onSuccess();

    void onFailure(int statusCode, String statusMessage, String errorMessage);

    void onFailure(IOException e);
}
