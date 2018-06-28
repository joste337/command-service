package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("shortcut")
public class CreateShortcutCommand extends AbstractCommand {
    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        UserSettings newShortcut = new UserSettings(StringUtils.split(userMessage, " ")[1], user.getCurrentProjectId(), user.getCurrentProjectName(), user.getCurrentServiceId(), user.getCurrentServiceName());
        user.addShortcut(newShortcut);
        return new CommandServiceReply(BotMessages.SUCCESSFULL_SHORTCUT_REPLY);
    }

    @Override
    public void isInvalidCommand(String userMessage) {
        if (StringUtils.split(userMessage, " ").length != 2) {
            throw new InvalidCommandOptionsException();
        }
    }


    @Override
    public String getDescription() {
        return null;
    }
}
