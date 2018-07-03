package jos.service.command.command;


import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import jos.service.command.util.CommandDescriptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("new")
public class NewCommand implements Command {
    @Autowired
    private MiteService miteService;

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        return executeCommandAndGetReply(splitUserMessage, user, null);
    }

    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user, UserSettings userSettings) {
        validate(splitUserMessage);

        String durationInMinutes = getDurationInMinutes(splitUserMessage[1]);
        String comment = splitUserMessage[2];

        if (userSettings == null) {
            miteService.newEntry(user, user.getCurrentSettingsAsUserSettings(), durationInMinutes, comment);
        } else {
            miteService.newEntry(user, userSettings, durationInMinutes, comment);
        }
        return new CommandServiceReply(String.format(BotMessages.F_SUCCESSFUL_ENTRY_REPLY, durationInMinutes, comment));
    }


    private void validate(String[] splitUserMessage) {
        if (splitUserMessage.length < 3 || !isValidDuration(splitUserMessage[1])) {
            throw new InvalidCommandOptionsException();
        }
    }

    private boolean isValidDuration(String duration) {
        return Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches();
    }

    private String getDurationInMinutes(String duration) {
        String[] split = StringUtils.split(duration, ":");
        return String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
    }

    @Override
    public String getDescription() {
        return CommandDescriptions.NEW_COMMAND_DESCRIPTION;
    }
}
