package main.java.com.mb3364.twitch.api;

import main.java.com.mb3364.twitch.http.HttpResponse;
import main.java.com.mb3364.twitch.api.models.*;
import main.java.com.mb3364.twitch.api.models.Error;
import main.java.com.mb3364.twitch.http.HttpResponse;

/**
 * TwitchResponse represents a response to a request from the Twitch API.
 *
 * @param <T> the object type of the data contained in a successful response
 * @author Matthew Bell
 */
public class TwitchResponse<T> {

    private final int statusCode; // HTTP status code of the response
    private final String statusText; // HTTP status text of the response
    private String errorMessage; // error message if an error was returned from the API
    private T object; // the payload object of a successful response

    /**
     * Construct a TwitchResponse with the http status code and status text.
     *
     * @param statusCode the HTTP status code of the response
     * @param statusText the HTTP status text of the response
     */
    public TwitchResponse(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    /**
     * Construct a TwitchResponse with a http status code, text, and an error message.
     *
     * @param statusCode   the HTTP status code of the response
     * @param statusText   the HTTP status text of the response
     * @param errorMessage the error message returned by the Twitch API
     */
    public TwitchResponse(int statusCode, String statusText, String errorMessage) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.errorMessage = errorMessage;
    }

    /**
     * Construct a TwitchResponse from a {@link HttpResponse}.
     *
     * @param httpResponse the {@link HttpResponse} to copy
     */
    public TwitchResponse(HttpResponse httpResponse) {
        this.statusCode = httpResponse.getCode();
        this.statusText = httpResponse.getMessage();
    }

    /**
     * Construct a TwitchResponse from a Twitch {@link main.java.com.mb3364.twitch.api.models.Error} response.
     *
     * @param error the {@link Error} response from the Twitch API
     */
    public TwitchResponse(Error error) {
        this.statusCode = error.getStatusCode();
        this.statusText = error.getStatusText();
        this.errorMessage = error.getMessage();
    }

    /**
     * Check to see if response is an error.
     *
     * @return <code>true</code> if error exists, <code>false</code> otherwise
     */
    public boolean hasError() {
        return errorMessage != null || object == null;
    }

    @Override
    public String toString() {
        return "TwitchResponse{" +
                "statusCode=" + statusCode +
                ", statusText='" + statusText + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", object=" + object +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitchResponse<?> that = (TwitchResponse<?>) o;

        if (statusCode != that.statusCode) return false;
        if (statusText != null ? !statusText.equals(that.statusText) : that.statusText != null) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        return !(object != null ? !object.equals(that.object) : that.object != null);
    }

    @Override
    public int hashCode() {
        int result = statusCode;
        result = 31 * result + (statusText != null ? statusText.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }

    /**
     * Get the HTTP status code of the response.
     *
     * @return the HTTP status code of the response
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the HTTP status text of the response.
     *
     * @return the HTTP status text of the response
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * Get the error message (if it exists) from the Twitch API.
     * If the error message is <code>null</code>, then there was not an error and a
     * successful request was made.
     *
     * @return the error message if an error occurred; <code>null</code> otherwise
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the error message of this {@link TwitchResponse} object.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the payload object of a successful response from the Twitch API.
     * This object will contain the data object that the Twitch API responded with.
     * If an error occurred, <code>null</code> will be returned.
     *
     * @return the data from the Twitch API request; <code>null</code> if an error occurred
     * during the request.
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets the payload object.
     *
     * @param object the payload object.
     */
    public void setObject(T object) {
        this.object = object;
    }
}
