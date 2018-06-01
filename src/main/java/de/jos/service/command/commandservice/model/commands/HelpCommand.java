package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;

public class HelpCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return botMessages.getHelpReply();
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
