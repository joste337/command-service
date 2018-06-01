package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public interface Command {
    String executeCommandAndGetReply(String userMessage, User user);

    boolean isValidCommand(String userMessage);
}