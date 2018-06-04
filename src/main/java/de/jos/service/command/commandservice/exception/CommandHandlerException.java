package de.jos.service.command.commandservice.exception;

import de.jos.service.command.commandservice.model.BotReply;

public class CommandHandlerException extends RuntimeException {
    private BotReply botReply;

    public CommandHandlerException(String message, BotReply botReply) {
        super(message);
        this.botReply = botReply;
    }

    public BotReply getBotReply() {
        return botReply;
    }

    public void setBotReply(BotReply botReply) {
        this.botReply = botReply;
    }
}
