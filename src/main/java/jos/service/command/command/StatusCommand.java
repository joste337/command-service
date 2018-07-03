package jos.service.command.command;


import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import org.springframework.stereotype.Component;


@Component("status")
public class StatusCommand implements Command {
    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        return new CommandServiceReply(user.getStatus());
    }

    @Override
    public String getDescription() {
        return null;
    }
}
