package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecuteShortcutCommand {
    @Autowired
    private NewCommand newCommand;
    @Autowired
    private MiteService miteService;

    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user, UserSettings userSettings) {
        newCommand.validate(userMessage, user);

        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = newCommand.getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];
        miteService.newEntry(user, userSettings, durationInMinutes, comment);

        return new CommandServiceReply(String.format(BotMessages.F_SUCCESSFUL_ENTRY_REPLY, durationInMinutes, comment));
    }
}
