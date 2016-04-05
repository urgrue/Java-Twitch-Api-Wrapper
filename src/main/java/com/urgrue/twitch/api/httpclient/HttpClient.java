package com.urgrue.twitch.api.httpclient;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class HttpClient {

    private final Map<String, String> headers; // HTTP request headers

    public HttpClient() {
        headers = Collections.synchronizedMap(new LinkedHashMap<String, String>());
    }

    /**
     * Make a HTTP DELETE request.
     *
     * @param url     the URL of the resource to request
     * @param handler the response handler
     * @see #delete(String, RequestParams, HttpResponseHandler)
     */
    public abstract void delete(final String url, final HttpResponseHandler handler);

    /**
     * Make a HTTP DELETE request with parameters.
     *
     * @param url     the URL of the resource to request
     * @param params  the parameters to send with the request
     * @param handler the response handler
     * @see #delete(String, HttpResponseHandler)
     */
    public abstract void delete(final String url, final RequestParams params, final HttpResponseHandler handler);

    /**
     * Make a HTTP GET request.
     *
     * @param url     the URL of the resource to request
     * @param handler the response handler
     * @see #get(String, RequestParams, HttpResponseHandler)
     */
    public abstract void get(final String url, final HttpResponseHandler handler);

    /**
     * Make a HTTP GET request with parameters.
     *
     * @param url     the URL of the resource to request
     * @param params  the parameters to send with the request
     * @param handler the response handler
     * @see #get(String, HttpResponseHandler)
     */
    public abstract void get(final String url, final RequestParams params, final HttpResponseHandler handler);

    /**
     * Make a HTTP HEAD request.
     *
     * @param url     the URL of the resource to request
     * @param handler the response handler
     * @see #head(String, RequestParams, HttpResponseHandler)
     */
    public abstract void head(final String url, final HttpResponseHandler handler);

    /**
     * Make a HTTP HEAD request with parameters.
     *
     * @param url     the URL of the resource to request
     * @param params  the parameters to send with the request
     * @param handler the response handler
     * @see #head(String, HttpResponseHandler)
     */
    public abstract void head(final String url, final RequestParams params, final HttpResponseHandler handler);

    /**
     * Make a HTTP POST request.
     *
     * @param url     the URL of the resource to request
     * @param handler the response handler
     * @see #post(String, RequestParams, HttpResponseHandler)
     */
    public abstract void post(final String url, final HttpResponseHandler handler);

    /**
     * Make a HTTP POST request with parameters.
     *
     * @param url     the URL of the resource to request
     * @param params  the parameters to send with the request
     * @param handler the response handler
     * @see #post(String, HttpResponseHandler)
     */
    public abstract void post(final String url, final RequestParams params, final HttpResponseHandler handler);

    /**
     * Make a HTTP PUT request.
     *
     * @param url     the URL of the resource to request
     * @param handler the response handler
     * @see #put(String, RequestParams, HttpResponseHandler)
     */
    public abstract void put(final String url, final HttpResponseHandler handler);

    /**
     * Make a HTTP PUT request with parameters.
     *
     * @param url     the URL of the resource to request
     * @param params  the parameters to send with the request
     * @param handler the response handler
     * @see #put(String, HttpResponseHandler)
     */
    public abstract void put(final String url, final RequestParams params, final HttpResponseHandler handler);

    /**
     * Set a global HTTP header that will be sent with all future requests.
     *
     * @param name  the header name
     * @param value the header value
     * @see #removeHeader(String)
     */
    public void setHeader(final String name, final String value) {
        headers.put(name, value);
    }

    /**
     * Remove a global HTTP header so it is no longer sent with all future requests.
     *
     * @param name the name of the header to remove
     * @see #setHeader(String, String)
     */
    public void removeHeader(final String name) {
        headers.remove(name);
    }

    /**
     * Gets the assigned global headers for this client.
     *
     * These headers will be sent with every request originating from this
     * instance.
     *
     * @return a {@link Map} of the assigned global headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }
}