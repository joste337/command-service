package de.jos.service.command.commandservice.model;

public class BotReply {
    private String message;
    private MessageOption[] messageOptions;

    public BotReply() {
    }

    public BotReply(String message) {
        this.message = message;
    }

    public BotReply(String message, MessageOption[] messageOptions) {
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
}
