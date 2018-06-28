package jos.service.command.exception;


import jos.service.command.util.BotMessages;

public class InvalidCommandOptionsException extends RuntimeException {
    public InvalidCommandOptionsException() {
        super(BotMessages.INVALID_COMMAND_ARGUMENTS_REPLY);
    }
}
