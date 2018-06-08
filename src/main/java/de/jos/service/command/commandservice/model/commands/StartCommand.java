package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import org.springframework.stereotype.Component;

public class StartCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return botMessages.getStartReply();
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
