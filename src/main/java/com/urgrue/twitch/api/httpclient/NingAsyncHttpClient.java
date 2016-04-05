package com.urgrue.twitch.api.httpclient;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import com.ning.http.client.providers.jdk.JDKAsyncHttpProvider;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NingAsyncHttpClient extends HttpClient {

    private static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(); // can reuse

    public NingAsyncHttpClient() {
        super();
        new AsyncHttpClient(new JDKAsyncHttpProvider(new AsyncHttpClientConfig.Builder().build()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String url, final HttpResponseHandler handler) {
        delete(url, new RequestParams(), handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String url, final RequestParams params, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.prepareDelete(url)
                    .setHeaders(convertHeaders(getHeaders()))
                    .setParameters(convertParams(params))
                    .execute(new AsyncCompletionHandler<Response>() {

                        @Override
                        public Response onCompleted(Response response) throws Exception {
                            int statusCode = response.getStatusCode();
                            Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                            String responseString = response.getResponseBody();

                            if (!isSuccessful(statusCode)) {
                                handler.onFailure(statusCode, headers, responseString);
                                return response;
                            }

                            handler.onSuccess(statusCode, headers, responseString);
                            return response;
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onFailure(t);
                        }
                    });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void get(final String url, final HttpResponseHandler handler) {
        get(url, new RequestParams(), handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void get(final String url, final RequestParams params, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.prepareGet(url)
                    .setHeaders(convertHeaders(getHeaders()))
                    .setParameters(convertParams(params))
                    .execute(new AsyncCompletionHandler<Response>() {

                        @Override
                        public Response onCompleted(Response response) throws Exception {
                            int statusCode = response.getStatusCode();
                            Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                            String responseString = response.getResponseBody();

                            if (!isSuccessful(statusCode)) {
                                handler.onFailure(statusCode, headers, responseString);
                                return response;
                            }

                            handler.onSuccess(statusCode, headers, responseString);
                            return response;
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onFailure(t);
                        }
                    });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void head(final String url, final HttpResponseHandler handler) {
        head(url, new RequestParams(), handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void head(final String url, final RequestParams params, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.prepareHead(url)
                    .setHeaders(convertHeaders(getHeaders()))
                    .setParameters(convertParams(params))
                    .execute(new AsyncCompletionHandler<Response>() {

                        @Override
                        public Response onCompleted(Response response) throws Exception {
                            int statusCode = response.getStatusCode();
                            Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                            String responseString = response.getResponseBody();

                            if (!isSuccessful(statusCode)) {
                                handler.onFailure(statusCode, headers, responseString);
                                return response;
                            }

                            handler.onSuccess(statusCode, headers, responseString);
                            return response;
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onFailure(t);
                        }
                    });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(final String url, final HttpResponseHandler handler) {
        post(url, new RequestParams(), handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(final String url, final RequestParams params, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.preparePost(url)
                    .setHeaders(convertHeaders(getHeaders()))
                    .setParameters(convertParams(params))
                    .execute(new AsyncCompletionHandler<Response>() {

                        @Override
                        public Response onCompleted(Response response) throws Exception {
                            int statusCode = response.getStatusCode();
                            Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                            String responseString = response.getResponseBody();

                            if (!isSuccessful(statusCode)) {
                                handler.onFailure(statusCode, headers, responseString);
                                return response;
                            }

                            handler.onSuccess(statusCode, headers, responseString);
                            return response;
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onFailure(t);
                        }
                    });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(final String url, final HttpResponseHandler handler) {
        put(url, new RequestParams(), handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(final String url, final RequestParams params, final HttpResponseHandler handler) {
        try {
            asyncHttpClient.preparePut(url)
                    .setHeaders(convertHeaders(getHeaders()))
                    .setParameters(convertParams(params))
                    .execute(new AsyncCompletionHandler<Response>() {

                        @Override
                        public Response onCompleted(Response response) throws Exception {
                            int statusCode = response.getStatusCode();
                            Map<String, List<String>> headers = new HashMap<>(response.getHeaders());
                            String responseString = response.getResponseBody();

                            if (!isSuccessful(statusCode)) {
                                handler.onFailure(statusCode, headers, responseString);
                                return response;
                            }

                            handler.onSuccess(statusCode, headers, responseString);
                            return response;
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            handler.onFailure(t);
                        }
                    });
        } catch (IOException e) {
            handler.onFailure(e);
        }
    }

    /**
     * Converts a {@link RequestParams} object into the object type that the
     * {@link AsyncHttpClient} expects.
     *
     * @param params The parameters that should be sent with the HTTP request.
     * @return A {@link Map} object of all the request parameters.
     */
    private Map<String, Collection<String>> convertParams(RequestParams params) {
        Map<String, Collection<String>> ningParams = new HashMap<String, Collection<String>>();
        for (ConcurrentHashMap.Entry<String, String> param : params.entrySet()) {
            Collection<String> paramValues = new ArrayList<String>();
            paramValues.add(param.getValue());
            ningParams.put(param.getKey(), paramValues);
        }
        return ningParams;
    }

    /**
     * Converts the {@link HttpClient} headers object into the object type that the
     * {@link AsyncHttpClient} expects.
     *
     * @param headers The headers that should be sent with the HTTP request.
     * @return A {@link Map} object of all the Headers.
     */
    private Map<String, Collection<String>> convertHeaders(Map<String, String> headers) {
        Map<String, Collection<String>> ningHeaders = new HashMap<String, Collection<String>>();
        for (ConcurrentHashMap.Entry<String, String> param : headers.entrySet()) {
            Collection<String> paramValues = new ArrayList<String>();
            paramValues.add(param.getValue());
            ningHeaders.put(param.getKey(), paramValues);
        }
        return ningHeaders;
    }

    /**
     * Check if a HTTP response status code represents a successful
     * request or not.
     *
     * @param statusCode the HTTP response status code.
     * @return True if request was successful; otherwise, false.
     */
    private boolean isSuccessful(int statusCode) {
        return (statusCode >= 200 && statusCode <= 299);
    }
}
