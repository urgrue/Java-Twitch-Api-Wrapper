package com.urgrue.twitch.api.httpclient;

import com.urgrue.twitch.api.handlers.BaseFailureHandler;

import java.util.List;
import java.util.Map;

/**
 * Handles HTTP response's from the Twitch API.
 * <p>Since all Http failure logic is the same, we handle it all in one place: here.</p>
 */
public abstract class HttpResponseHandler {

    private BaseFailureHandler apiHandler;

    public HttpResponseHandler(BaseFailureHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    public abstract void onSuccess(int statusCode, Map<String, List<String>> headers, String content);

    public abstract void onFailure(int statusCode, Map<String, List<String>> headers, String content);

    public void onFailure(Throwable throwable) {
        apiHandler.onFailure(throwable);
    }

    protected BaseFailureHandler getApiHandler() {
        return apiHandler;
    }
}