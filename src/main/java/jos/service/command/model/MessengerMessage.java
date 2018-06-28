package jos.service.command.model;

import javax.validation.constraints.NotNull;

public class MessengerMessage {
    @NotNull
    private String message;
    @NotNull
    private String messengerId;
    @NotNull
    private String userName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessengerId() {
        return messengerId;
    }

    public void setMessengerId(String messengerId) {
        this.messengerId = messengerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "MessengerMessage{" +
                "message='" + message + '\'' +
                ", messengerId='" + messengerId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
