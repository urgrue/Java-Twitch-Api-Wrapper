package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.StreamsSummary;

public interface StreamsSummaryResponseHandler extends BaseFailureHandler {
    void onSuccess(StreamsSummary summary);
}
