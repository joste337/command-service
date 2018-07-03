package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("register")
public class RegisterCommand implements Command {
    @Autowired
    private MiteService miteService;

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        validate(splitUserMessage);

        user.setApiKey(splitUserMessage[1]);
        return new CommandServiceReply(BotMessages.API_KEY_SET_SUCCESSFULLY_REPLY);
    }

    private void validate(String[] splitUserMessage) {
        miteService.verifyApiKey(splitUserMessage[1]);

        if(splitUserMessage.length != 2) {
            throw new InvalidCommandOptionsException();
        }
    }
}