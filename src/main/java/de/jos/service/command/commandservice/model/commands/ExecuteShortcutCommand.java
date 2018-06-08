package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.database.model.UserSettings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ExecuteShortcutCommand extends AbstractCommand {
    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }

        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];
        UserSettings userSettings = user.getShortcuts().stream().filter(userSetting -> userSetting.getKey().equals(splitMessage[0])).findFirst().orElse(null);
        if (userSettings == null) {
            return botMessages.getInvalidCommandReply();
        }

        miteService.newEntry(user, userSettings, durationInMinutes, comment);
        return botMessages.getSuccessfullEntryReply(durationInMinutes, comment);
    }

    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if (splitMessage.length < 3) {
            return false;
        }
        return isValidDuration(splitMessage[1]);
    }

    @Override
    public String getDescription() {
        return null;
    }

    private boolean isValidDuration(String duration) {
        return Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches();
    }

    private String getDurationInMinutes(String duration) {
        String[] split = StringUtils.split(duration, ":");
        return String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
    }
}
