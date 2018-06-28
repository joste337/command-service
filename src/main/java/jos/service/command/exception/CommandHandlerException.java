package jos.service.command.exception;


import jos.service.command.model.CommandServiceReply;

public class CommandHandlerException extends RuntimeException {
    private CommandServiceReply botReply;

    public CommandHandlerException(String message, CommandServiceReply botReply) {
        super(message);
        this.botReply = botReply;
    }

    public CommandServiceReply getBotReply() {
        return botReply;
    }

    public void setBotReply(CommandServiceReply botReply) {
        this.botReply = botReply;
    }
}
