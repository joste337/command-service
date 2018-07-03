package jos.service.command.manager;

import jos.service.command.command.Command;
import jos.service.command.command.HelpCommand;
import jos.service.command.command.NewCommand;
import jos.service.command.command.RegisterCommand;
import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.MessengerMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class MessageHandleManager {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserManager userManager;
    @Autowired
    private NewCommand newCommand;

    public CommandServiceReply handleMessage(String token, MessengerMessage messengerMessage) throws UnsupportedEncodingException {
        String[] splitUserMessage = StringUtils.split(URLDecoder.decode(messengerMessage.getMessage(), "UTF-8"), " ", 3);

        Command command = applicationContext.getBean(splitUserMessage[0], Command.class);

        String userId = token + messengerMessage.getMessengerId();
        User user = userManager.getUserById(userId, messengerMessage.getUserName());


        try {
            return command.executeCommandAndGetReply(splitUserMessage, user);
        } catch (NoSuchBeanDefinitionException e) {
            UserSettings userSettings = user.getShortcuts().stream().filter(userSettings1 -> userSettings1.getKey().equals(splitUserMessage[0])).findFirst().orElseThrow(() -> new RuntimeException(""));
            return newCommand.executeCommandAndGetReply(splitUserMessage, user, userSettings);
        }
    }
}
