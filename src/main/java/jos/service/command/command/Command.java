package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;

public interface Command {
    String getDescription();

    CommandServiceReply executeCommandAndGetReply(String userMessage, User user);

    void isInvalidCommand(String userMessage);

    void hasSetPrerequisites(User user);

    void validate(String userMessage, User user);
}
