package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface Command {
    String getDescription();

    CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user);
}
