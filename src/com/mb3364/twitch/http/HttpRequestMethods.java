package com.mb3364.twitch.http;

public enum HttpRequestMethods {

    CONNECT("CONNECT"),
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    POST("POST"),
    PUT("PUT");

    String key;

    HttpRequestMethods(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
