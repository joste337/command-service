package de.jos.service.command.commandservice.model.commands;


import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;

public class StopCommand extends AbstractCommand {
    public BotReply executeCommandAndGetReply(String userMessage, User user) {
        return null;
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
