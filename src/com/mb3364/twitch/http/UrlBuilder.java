package com.mb3364.twitch.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder {

    private String scheme = "";
    private String host = "";
    private int port = 0;
    private String path = "";
    private Map<String, String> queryParams = new HashMap<>();

    public UrlBuilder scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public UrlBuilder host(String host) {
        this.host = host;
        return this;
    }

    public UrlBuilder port(int port) {
        this.port = port;
        return this;
    }

    public UrlBuilder path(String path) {
        this.path = path;
        return this;
    }

    public UrlBuilder param(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(scheme).append("://").append(host);

        if (port > 0) {
            sb.append(":").append(port);
        }

        if (path.charAt(0) != '/') {
            sb.append("/");
        }

        sb.append(path);

        if (queryParams.size() > 0) {
            sb.append("?");
            for (Map.Entry<String, String> param : queryParams.entrySet()) {
                String key = param.getKey();
                String value = param.getValue();

                try {
                    sb.append(URLEncoder.encode(key, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove last '&'
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}
