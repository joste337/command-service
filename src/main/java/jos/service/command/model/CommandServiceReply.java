package jos.service.command.model;


public class CommandServiceReply {
    private String message;
    private MessageOption[] messageOptions;

    public CommandServiceReply() {
    }

    public CommandServiceReply(String message) {
        this.message = message;
    }

    public CommandServiceReply(String message, MessageOption[] messageOptions) {
        this.message = message;
        this.messageOptions = messageOptions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageOption[] getMessageOptions() {
        return messageOptions;
    }

    public void setMessageOptions(MessageOption[] messageOptions) {
        this.messageOptions = messageOptions;
    }

    @Override
    public String toString() {
        String toString = "CommandServiceResponse: " + "message=" + message;
        if (messageOptions != null) {
            toString += ", messageOptions=" + messageOptions.toString();
        }
        return toString;
    }
}
