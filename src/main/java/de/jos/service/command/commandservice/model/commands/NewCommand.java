package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("new")
public class NewCommand extends AbstractCommand {

    public BotReply executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return new BotReply(botMessages.getInvalidCommandArgumentsReply());
        }

        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];

        miteService.newEntry(user, user.getCurrentSettingsAsUserSettings(), durationInMinutes, comment);
        return new BotReply(botMessages.getSuccessfullEntryReply(durationInMinutes, comment));
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

    private String getEntryParametersForUrl(String duration, String comment, User user) {
        return "?apiKey=" + user.getApiKey() + "&duration=" + duration + "&projectId=" + user.getCurrentProjectId() + "&serviceId=" + user.getCurrentServiceId() + "&comment=" + comment;
    }
}
