package com.urgrue.twitch.api.handlers;

import com.urgrue.twitch.api.models.Stream;

public interface StreamResponseHandler extends BaseFailureHandler {
    /**
     * API callback was successful.
     *
     * @param stream the stream object. Will be <code>null</code> if the stream is offline!
     */
    void onSuccess(Stream stream);
}
