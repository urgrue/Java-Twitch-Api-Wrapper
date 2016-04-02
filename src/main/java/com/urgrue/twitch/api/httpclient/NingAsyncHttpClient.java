package com.urgrue.twitch.api.httpclient;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NingAsyncHttpClient extends HttpClient {

    protected static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(); // can reuse

    public NingAsyncHttpClient() {
        super();
    }

    @Override
    public void delete(String url, HttpResponseHandler handler) {

    }

    @Override
    public void delete(String url, RequestParams params, HttpResponseHandler handler) {

    }

    @Override
    public void get(final String url, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.prepareGet(url).execute(new AsyncCompletionHandler<Void>(){

                @Override
                public Void onCompleted(Response response) throws Exception{
                    int statusCode = response.getStatusCode();
                    Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                    String responseString = response.getResponseBody();

                    if (statusCode < 200 || statusCode > 299) {
                        handler.onFailure(statusCode, headers, responseString);
                        return null;
                    }

                    handler.onSuccess(statusCode, headers, responseString);
                    return null;
                }

                @Override
                public void onThrowable(Throwable t){
                    handler.onFailure(t);
                }
            });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    @Override
    public void get(String url, RequestParams params, HttpResponseHandler handler) {

    }

    @Override
    public void head(String url, HttpResponseHandler handler) {

    }

    @Override
    public void head(String url, RequestParams params, HttpResponseHandler handler) {

    }

    @Override
    public void post(String url, HttpResponseHandler handler) {

    }

    @Override
    public void post(String url, RequestParams params, HttpResponseHandler handler) {

    }

    @Override
    public void put(String url, HttpResponseHandler handler) {

    }

    @Override
    public void put(String url, RequestParams params, HttpResponseHandler handler) {

    }
}
