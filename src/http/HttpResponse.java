package http;

public class HttpResponse {

    private int code;
    private String message;
    private String content;

    public HttpResponse(int code, String message, String content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public HttpResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
