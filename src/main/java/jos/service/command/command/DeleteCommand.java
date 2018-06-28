package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import org.springframework.stereotype.Component;


@Component("delete")
public class DeleteCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        CommandServiceReply reply =  miteService.getDeleteEntryOptions(user, user.getName());
        System.out.println("reply: " + reply.toString());

//        try {
//            messageOptions = new ObjectMapper().readValue(reply.get("messageOption").toString(), MessageOption[].class);
//        } catch (Exception e) {
//            throw new CommandHandlerException(e.getMessage(), new BotReply(BotMessages.GENERAL_ERROR_REPLY));
//        }
        reply.setMessage("abc");
        System.out.println("reply: " + reply.toString());
        return reply;
    }
}
