package jos.service.command.command;


import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("status")
public class StatusCommand extends AbstractCommand {
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        return new CommandServiceReply(user.toReplyString());
    }

    @Override
    public String getDescription() {
        return null;
    }
}
