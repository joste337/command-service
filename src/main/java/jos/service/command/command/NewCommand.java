package jos.service.command.command;


import jos.service.command.database.model.User;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component("new")
public class NewCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];

        miteService.newEntry(user, user.getCurrentSettingsAsUserSettings(), durationInMinutes, comment);
        return new CommandServiceReply(String.format(BotMessages.F_SUCCESSFUL_ENTRY_REPLY, durationInMinutes, comment));
    }

    @Override
    public void isInvalidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if (splitMessage.length < 3 || !isValidDuration(splitMessage[1])) {
            throw new InvalidCommandOptionsException();
        }
    }

    private boolean isValidDuration(String duration) {
        return Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches();
    }

    public String getDurationInMinutes(String duration) {
        String[] split = StringUtils.split(duration, ":");
        return String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
    }

    private String getEntryParametersForUrl(String duration, String comment, User user) {
        return "?apiKey=" + user.getApiKey() + "&duration=" + duration + "&projectId=" + user.getCurrentProjectId() + "&serviceId=" + user.getCurrentServiceId() + "&comment=" + comment;
    }
}
