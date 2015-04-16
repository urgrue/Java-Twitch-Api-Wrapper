package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClient {

    private int connectionTimeout = 20000; // Connection timeout in ms
    private int dataRetrievalTimeout = 20000; // Data retrieval timeout in ms

    /**
     * Read the input stream and convert to a string
     *
     * @param inputStream InputStream to read
     * @return String representing entire input stream contents
     * @throws IOException
     */
    private static String readStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder text = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line);
        }

        reader.close();
        return text.toString();
    }

    /**
     * Make a HTTP Request.
     *
     * @param resourceUrl   Resource URL
     * @param requestMethod Request Method: GET, POST, PUT, DELETE, etc.
     * @param inputData     Body data to send with HTTP request
     * @param headers       Headers to include with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     */
    public HttpResponse request(
            String resourceUrl,
            HttpRequestMethods requestMethod,
            Map<String, String> headers,
            Map<String, String> inputData)
            throws IOException {

        HttpResponse response = null;

        URL url = new URL(resourceUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            // Settings
            urlConnection.setConnectTimeout(connectionTimeout);
            urlConnection.setReadTimeout(dataRetrievalTimeout);
            urlConnection.setRequestMethod(requestMethod.toString());
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);

            // Headers
            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    urlConnection.addRequestProperty(header.getKey(), header.getValue());
                }
            }

            // Input data
            String input = "";
            if (inputData != null) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> datum : inputData.entrySet()) {
                    sb.append(datum.getKey());
                    sb.append("=");
                    sb.append(URLEncoder.encode(datum.getValue(), "UTF-8"));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1); // Remove final '&'
                input = sb.toString();

                urlConnection.setDoOutput(true); // Send data in request body
                urlConnection.setFixedLengthStreamingMode(input.length()); // More efficient since length known

                // Set headers for input content
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setRequestProperty("Content-Length", Integer.toString(input.length()));
            }

            urlConnection.connect();

            // Write Input Data
            if (inputData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(input);
                writer.flush();
                writer.close();
            }

            // Response
            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            response = new HttpResponse(responseCode, responseMessage);

            // 'Successful' response codes will be in interval [200,300)
            if (responseCode >= 200 && responseCode < 300) {
                String content = readStream(urlConnection.getInputStream());
                response.setContent(content);
            } else {
                String content = readStream(urlConnection.getErrorStream());
                response.setContent(content);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return response;
    }

    /**
     * Make a HTTP GET Request
     *
     * @param resourceUrl Resource URL
     * @param headers     Headers to include with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #get(String)
     */
    public HttpResponse get(String resourceUrl, Map<String, String> headers) throws IOException {
        return request(resourceUrl, HttpRequestMethods.GET, headers, null);
    }

    /**
     * Make a HTTP GET Request
     *
     * @param resourceUrl Resource URL
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #get(String, Map)
     */
    public HttpResponse get(String resourceUrl) throws IOException {
        return get(resourceUrl, null);
    }

    /**
     * Make a HTTP PUT Request
     *
     * @param resourceUrl Resource URL
     * @param headers     Headers to include with HTTP request
     * @param data        Body data to send with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #put(String)
     * @see #put(String, Map)
     */
    public HttpResponse put(String resourceUrl, Map<String, String> headers, Map<String, String> data) throws IOException {
        return request(resourceUrl, HttpRequestMethods.PUT, headers, data);
    }

    /**
     * Make a HTTP PUT Request
     *
     * @param resourceUrl Resource URL
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #put(String, Map)
     * @see #put(String, Map, Map)
     */
    public HttpResponse put(String resourceUrl) throws IOException {
        return put(resourceUrl, null, null);
    }

    /**
     * Make a HTTP PUT Request
     *
     * @param resourceUrl Resource URL
     * @param headers     Headers to include with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #put(String)
     * @see #put(String, Map, Map)
     */
    public HttpResponse put(String resourceUrl, Map<String, String> headers) throws IOException {
        return put(resourceUrl, null, headers);
    }

    /**
     * Make a HTTP DELETE Request
     *
     * @param resourceUrl Resource URL
     * @param headers     Headers to include with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #delete(String)
     */
    public HttpResponse delete(String resourceUrl, Map<String, String> headers) throws IOException {
        return request(resourceUrl, HttpRequestMethods.DELETE, headers, null);
    }

    /**
     * Make a HTTP DELETE Request
     *
     * @param resourceUrl Resource URL
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     * @see #put(String, Map)
     */
    public HttpResponse delete(String resourceUrl) throws IOException {
        return delete(resourceUrl, null);
    }

    /**
     * Make a HTTP POST Request
     *
     * @param resourceUrl Resource URL
     * @param headers     Headers to include with HTTP request
     * @param data        Body data to send with HTTP request
     * @return HttpResponse Response from HTTP request
     * @throws IOException Error during HTTP request
     */
    public HttpResponse post(String resourceUrl, Map<String, String> headers, Map<String, String> data) throws IOException {
        return request(resourceUrl, HttpRequestMethods.POST, headers, data);
    }

    /**
     * Get connection timeout value (in milliseconds)
     *
     * @return Connection timeout value (in milliseconds)
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Set connection timeout value (in milliseconds)
     *
     * @param connectionTimeout New timeout value in milliseconds
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Get Data Retrieval timeout value
     *
     * @return Data Retrieval timeout value
     */
    public int getDataRetrievalTimeout() {
        return dataRetrievalTimeout;
    }

    /**
     * Set Data Retrieval timeout
     *
     * @param dataRetrievalTimeout New timeout value in milliseconds
     */
    public void setDataRetrievalTimeout(int dataRetrievalTimeout) {
        this.dataRetrievalTimeout = dataRetrievalTimeout;
    }
}

