package de.jos.service.command.commandservice.model;

public class BotReply {
    private String message;

    public BotReply() {
    }

    public BotReply(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
