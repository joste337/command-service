package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("register")
public class RegisterCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        validate(userMessage, user);
        user.setApiKey(StringUtils.split(userMessage, " ")[1]);
        return new CommandServiceReply(BotMessages.API_KEY_SET_SUCCESSFULLY_REPLY);
    }

    @Override
    public void isInvalidCommand(String userMessage) {
        miteService.verifyApiKey(StringUtils.split(userMessage, " ")[1]);
        if(StringUtils.split(userMessage, " ").length != 2) {
            throw new InvalidCommandOptionsException();
        }
    }
}