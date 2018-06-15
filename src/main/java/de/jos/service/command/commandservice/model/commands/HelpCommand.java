package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.springframework.stereotype.Component;

@Component("help")
public class HelpCommand extends AbstractCommand {
    public BotReply executeCommandAndGetReply(String commandMessage, User user) {
        return new BotReply(botMessages.getHelpReply());
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
