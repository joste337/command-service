package de.jos.service.command.commandservice.model.commands;


import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import org.springframework.stereotype.Component;

@Component("status")
public class StatusCommand extends AbstractCommand {
    public BotReply executeCommandAndGetReply(String userMessage, User user) {
        return new BotReply(user.toReplyString());
    }

    public boolean isValidCommand(String userMessage) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
