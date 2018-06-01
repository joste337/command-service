package de.jos.service.command.commandservice.model.commands;


import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.database.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }
        user.setApiKey(StringUtils.split(userMessage, " ")[1]);
        return botMessages.getSuccessfullyRegisteredReply();
    }

    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length == 2;
    }
}
