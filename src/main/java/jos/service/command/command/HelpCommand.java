package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.springframework.stereotype.Component;


@Component("help")
public class HelpCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String commandMessage, User user) {
        return new CommandServiceReply(BotMessages.HELP_REPLY);
    }
}
