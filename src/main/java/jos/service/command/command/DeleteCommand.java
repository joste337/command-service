package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("delete")
public class DeleteCommand implements Command {
    @Autowired
    private MiteService miteService;

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        CommandServiceReply reply =  miteService.getDeleteEntryOptions(user, user.getName());
        reply.setMessage("Choose which entry you want to delete:");
        return reply;
    }

    @Override
    public String getDescription() {
        return null;
    }

}
