package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.springframework.stereotype.Component;

public class StartCommand extends AbstractCommand {
    public BotReply executeCommandAndGetReply(String commandMessage, User user) {
        return new BotReply(botMessages.getStartReply());
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
