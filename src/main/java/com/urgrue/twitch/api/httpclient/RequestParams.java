package com.urgrue.twitch.api.httpclient;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parameters to send with HTTP requests. These can be {@link String}'s or {@link File}'s.
 * Other primitive types will be converted to {@link String} before being sent.
 */
public class RequestParams {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private ConcurrentHashMap<String, String> stringParams = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, File> fileParams = new ConcurrentHashMap<>();

    private Charset charset;

    /**
     * Create an empty Request Params object.
     */
    public RequestParams() {
        charset = DEFAULT_CHARSET;
    }

    /**
     * Create a RequestParams object with an initial key value pair.
     *
     * @param key   the parameter key
     * @param value the parameter value
     */
    public RequestParams(String key, String value) {
        this();
        stringParams.put(key, value);
    }

    /**
     * Create a RequestParams object with an initial file.
     *
     * @param key  the parameter key
     * @param file the parameter {@link File} object to upload
     */
    public RequestParams(String key, File file) {
        this();
        fileParams.put(key, file);
    }

    /**
     * Check if this object contains a parameter with the specified key.
     *
     * @param key the parameter key to check
     * @return <code>true</code> if parameter exists, <code>false</code> otherwise
     */
    public boolean containsKey(String key) {
        return stringParams.containsKey(key) || fileParams.containsKey(key);
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
     * Set a new {@link File} parameter or overwrite and existing one.
     *
     * @param key  the parameter key
     * @param file the parameter {@link File} object
     */
    public void put(String key, File file) {
        fileParams.put(key, file);
    }

    /**
     * Get an existing value in {@link String} format.
     *
     * @param key the parameter key
     * @return the value of the specified parameter in {@link String} format
     * @see #getFile(String)
     * @see #remove(String)
     */
    public String getString(String key) {
        return stringParams.get(key);
    }

    /**
     * Get an existing {@link File} value.
     *
     * @param key the parameter key
     * @return the {@link File} value of the specifed parameter
     * @see #getString(String)
     * @see #remove(String)
     */
    public File getFile(String key) {
        return fileParams.get(key);
    }

    /**
     * Remove a parameter.
     *
     * @param key the key of the parameter to remove
     * @see #getFile(String)
     * @see #getString(String)
     */
    public void remove(String key) {
        stringParams.remove(key);
    }

    /**
     * Get the {@link Set} representation of all non-File parameters.
     *
     * @return all non-File parameter key-value pairs
     * @see #fileEntrySet()
     */
    public Set<Map.Entry<String, String>> stringEntrySet() {
        return stringParams.entrySet();
    }

    /**
     * Get the {@link Set} representation of all File parameters.
     *
     * @return all of the {@link File} parameter's key-value pairs
     * @see #stringEntrySet()
     */
    public Set<Map.Entry<String, File>> fileEntrySet() {
        return fileParams.entrySet();
    }

    /**
     * Check if any {@link File} parameter's exist.
     *
     * @return <code>true</code> if at least 1 {@link File} parameter exists, <code>false</code> otherwise
     */
    public boolean hasFiles() {
        return fileParams.size() > 0;
    }

    /**
     * Get the number of existing parameters.
     *
     * @return the number of parameters
     */
    public int size() {
        return stringParams.size() + fileParams.size();
    }

    /**
     * Get the currently set Charset of the values in this object. If not explicitly set, UTF_8 is default.
     *
     * @return current Charset of parameter values
     * @see #setCharset(Charset)
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * Set the Charset of the values in this object for encoding purposes. UTF_8 is default.
     *
     * @param charset the new {@link Charset}
     * @see #getCharset()
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /**
     * Encodes parameters into a query string based on the charset.
     *
     * @return the parameters as an encoded query string
     * @see #setCharset(Charset)
     * @see #getCharset()
     */
    public String toEncodedString() {
        try {
            StringBuilder encoded = new StringBuilder();
            for (ConcurrentHashMap.Entry<String, String> param : stringParams.entrySet()) {
                if (encoded.length() > 0) encoded.append("&");
                encoded.append(URLEncoder.encode(param.getKey(), charset.name()));
                encoded.append("=");
                encoded.append(URLEncoder.encode(param.getValue(), charset.name()));
            }

            return encoded.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}

