package de.jos.service.command.commandservice.model.commands;


import de.jos.service.command.commandservice.database.model.User;

public class StatusCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String userMessage, User user) {
        return user.toReplyString();
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
