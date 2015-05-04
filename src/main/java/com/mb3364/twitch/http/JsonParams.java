package main.java.com.mb3364.twitch.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class JsonParams extends HashMap<String, String> {

    public void put(String key, int value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, double value) {
        put(key, String.valueOf(value));
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(get(key));
    }

    public String toQueryString() {
        StringBuilder sb = new StringBuilder();
        if (size() > 0) {
            for (Map.Entry<String, String> datum : entrySet()) {
                sb.append(datum.getKey());
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(datum.getValue(), "UTF-8"));
                } catch (IOException e) {
                    sb.append("");
                }
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove final '&'
        }
        return sb.toString();
    }
}
