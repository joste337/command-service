package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.springframework.stereotype.Component;

@Component("shortcut")
public class CreateShortcutCommand implements Command {
    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        validate(splitUserMessage, user);

        UserSettings newShortcut = new UserSettings(splitUserMessage[1], user.getCurrentProjectId(), user.getCurrentProjectName(), user.getCurrentServiceId(), user.getCurrentServiceName());
        user.addShortcut(newShortcut);

        return new CommandServiceReply(BotMessages.SUCCESSFULL_SHORTCUT_REPLY);
    }

    private void validate(String[] splitUserMessage, User user) {
        if (splitUserMessage.length != 2) {
            throw new InvalidCommandOptionsException();
        }

        if (user.getCurrentProjectId() == null || user.getCurrentServiceId() == null) {
            throw new InvalidCommandOptionsException();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
