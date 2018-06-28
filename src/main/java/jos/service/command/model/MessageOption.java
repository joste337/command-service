package jos.service.command.model;

import java.io.Serializable;

public class MessageOption implements Serializable {
    private String message;
    private String url;

    public MessageOption() {
    }

    public MessageOption(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MessageOption{" +
                "message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}