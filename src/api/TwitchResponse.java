package api;

import http.HttpResponse;

public class TwitchResponse<T> {

    private final int statusCode;
    private final String statusText;
    private String errorMessage;
    private T object;

    public TwitchResponse(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public TwitchResponse(HttpResponse httpResponse) {
        this.statusCode = httpResponse.getCode();
        this.statusText = httpResponse.getMessage();
    }

    public TwitchResponse(ErrorResponse errorResponse) {
        this.statusCode = errorResponse.getStatusCode();
        this.statusText = errorResponse.getStatusText();
        this.errorMessage = errorResponse.getMessage();
    }

    /**
     * Check to see if response is an error.
     * @return True if error exists. False otherwise.
     */
    public boolean hasError() {
        return errorMessage != null;
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

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
