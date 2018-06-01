package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class NewCommand extends AbstractCommand {

    public String executeCommandAndGetReply(String userMessage, User user) {
        System.out.println("trying to execute new command with message: " + userMessage);
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }

        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];

        return miteService.newEntry(user, durationInMinutes, comment);
    }

    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if (splitMessage.length < 3) {
            System.out.println("less than");
            return false;
        }
        return isValidDuration(splitMessage[1]);
    }

    private boolean isValidDuration(String duration) {
        return Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches();
    }

    private String getDurationInMinutes(String duration) {
        String[] split = StringUtils.split(duration, ":");
        return String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
    }

    private String getEntryParametersForUrl(String duration, String comment, User user) {
        return "?apiKey=" + user.getApiKey() + "&duration=" + duration + "&projectId=" + user.getProjectId() + "&serviceId=" + user.getServiceId() + "&comment=" + comment;
    }
}
