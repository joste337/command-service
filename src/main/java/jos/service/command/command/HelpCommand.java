package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("help")
public class HelpCommand implements Command {
    @Autowired
    private List<Command> commandList;

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        StringBuilder result = new StringBuilder();

        commandList.stream()
                .filter(command -> command.getDescription() != null)
                .forEach(command -> result.append(command.getDescription()).append("\n"));

        return new CommandServiceReply(result.toString());
    }
}
