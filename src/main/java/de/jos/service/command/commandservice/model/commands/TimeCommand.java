package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;

public class TimeCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String userMessage, User user) {
        return null;
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
