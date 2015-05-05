package com.mb3364.twitch.api.handlers;

import com.mb3364.twitch.api.models.StreamsSummary;

public interface StreamsSummaryResponseHandler extends BaseFailureHandler {
    void onSuccess(StreamsSummary summary);

    @Override
    void onFailure(int statusCode, String statusMessage, String errorMessage);

    @Override
    void onFailure(Throwable throwable);
}
