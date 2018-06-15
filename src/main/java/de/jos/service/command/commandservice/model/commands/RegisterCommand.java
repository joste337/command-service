package de.jos.service.command.commandservice.model.commands;


import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("register")
public class RegisterCommand extends AbstractCommand {
    public BotReply executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return new BotReply(botMessages.getInvalidCommandArgumentsReply());
        }
        miteService.verifyApiKey(StringUtils.split(userMessage, " ")[1]);
        user.setApiKey(StringUtils.split(userMessage, " ")[1]);
        return new BotReply(botMessages.getSuccessfullyRegisteredReply());
    }

    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length == 2;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
