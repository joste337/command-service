package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    String executeCommandAndGetReply(String userMessage, User user);

    boolean isValidCommand(String userMessage);

    String getDescription();
}