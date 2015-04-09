package api;

import http.HttpResponse;

public class TwitchResponse extends HttpResponse {

    public TwitchResponse(int code, String message, String content) {
        super(code, message, content);
    }

    public TwitchResponse(int code, String message) {
        super(code, message);
    }

    public TwitchResponse(HttpResponse httpResponse) {
        super(httpResponse.getCode(), httpResponse.getMessage(), httpResponse.getContent());
    }
}
