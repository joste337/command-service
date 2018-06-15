package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.database.model.UserSettings;
import de.jos.service.command.commandservice.model.BotReply;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("shortcut")
public class CreateShortcutCommand extends AbstractCommand {
    @Override
    public BotReply executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return new BotReply(botMessages.getInvalidCommandArgumentsReply());
        }
        UserSettings newShortcut = new UserSettings(StringUtils.split(userMessage, " ")[1], user.getCurrentProjectId(), user.getCurrentProjectName(), user.getCurrentServiceId(), user.getCurrentServiceName());
        user.addShortcut(newShortcut);
        return new BotReply(botMessages.getSuccessfullySetShortcutReply());
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length == 2;
    }


    @Override
    public String getDescription() {
        return null;
    }
}
