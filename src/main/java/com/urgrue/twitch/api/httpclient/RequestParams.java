package com.urgrue.twitch.api.httpclient;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parameters to send with the TwitchApiClient requests.
 * Primitive types will be converted to {@link String} before being sent.
 */
public class RequestParams {

    private ConcurrentHashMap<String, String> stringParams = new ConcurrentHashMap<>();

    /**
     * Create an empty RequestParams object.
     */
    public RequestParams() {
    }

    /**
     * Create a RequestParams object with an initial key value pair.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public RequestParams(String key, String value) {
        stringParams.put(key, value);
    }

    /**
     * Check if this object contains a parameter with the specified key.
     *
     * @param key the parameter key to check
     * @return <code>true</code> if parameter exists, <code>false</code> otherwise
     */
    public boolean containsKey(String key) {
        return stringParams.containsKey(key);
    }

    /**
     * Set a new {@link String} parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, String value) {
        stringParams.put(key, value);
    }

    /**
     * Set a new <code>short</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, short value) {
        stringParams.put(key, Short.toString(value));
    }

    /**
     * Set a new <code>int</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, int value) {
        stringParams.put(key, Integer.toString(value));
    }

    /**
     * Set a new <code>double</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, double value) {
        stringParams.put(key, Double.toString(value));
    }

    /**
     * Set a new <code>float</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, float value) {
        stringParams.put(key, Float.toString(value));
    }

    /**
     * Set a new <code>long</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, long value) {
        stringParams.put(key, Long.toString(value));
    }

    /**
     * Set a new <code>boolean</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, boolean value) {
        stringParams.put(key, Boolean.toString(value));
    }

    /**
     * Set a new <code>char</code> parameter or overwrite an existing one.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public void put(String key, char value) {
        stringParams.put(key, Character.toString(value));
    }

    /**
     * Copy an existing {@link Map <String, String>}'s key-value pairs into this object.
     *
     * @param otherMap the Map to copy the key-value pairs from
     */
    public void put(Map<String, String> otherMap) {
        stringParams.putAll(otherMap);
    }

    /**
     * Get an existing value in {@link String} format.
     *
     * @param key the parameter key
     * @return the value of the specified parameter in {@link String} format
     * @see #remove(String)
     */
    public String getString(String key) {
        return stringParams.get(key);
    }

    /**
     * Remove a parameter.
     *
     * @param key the key of the parameter to remove
     * @see #getString(String)
     */
    public void remove(String key) {
        stringParams.remove(key);
    }

    /**
     * Get the {@link Set} representation of all parameters.
     *
     * @return all parameter key-value pairs
     */
    public Set<Map.Entry<String, String>> entrySet() {
        return stringParams.entrySet();
    }

    /**
     * Get the number of existing parameters.
     *
     * @return the number of parameters
     */
    public int size() {
        return stringParams.size();
    }
}

