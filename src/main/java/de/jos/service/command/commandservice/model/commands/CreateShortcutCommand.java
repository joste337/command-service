package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.database.model.UserSettings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("shortcut")
public class CreateShortcutCommand extends AbstractCommand {
    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }
        UserSettings newShortcut = new UserSettings(StringUtils.split(userMessage, " ")[1], user.getCurrentProjectId(), user.getCurrentProjectName(), user.getCurrentServiceId(), user.getCurrentServiceName());
        user.addShortcut(newShortcut);
        return botMessages.getSuccessfullySetShortcutReply();
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
