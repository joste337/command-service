package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.service.MiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public abstract class AbstractCommand implements Command {
    @Autowired
    protected MiteService miteService;

    @Override
    public final void validate(String userMessage, User user) {
        this.hasSetPrerequisites(user);
        this.isInvalidCommand(userMessage);
    }

    @Override
    public void hasSetPrerequisites(User user) {
    }

    @Override
    public void isInvalidCommand(String userMessage) {
    }
}
