package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    BotReply executeCommandAndGetReply(String userMessage, User user);

    boolean isValidCommand(String userMessage);

    String getDescription();
}