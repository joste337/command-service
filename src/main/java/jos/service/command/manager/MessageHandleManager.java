package jos.service.command.manager;

import jos.service.command.command.Command;
import jos.service.command.command.ExecuteShortcutCommand;
import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.MessengerMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class MessageHandleManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandleManager.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ExecuteShortcutCommand executeShortcutCommand;

    public CommandServiceReply handleMessage(String clientToken, MessengerMessage messengerMessage) throws UnsupportedEncodingException {
        LOGGER.info("Incoming messengerMessage: {}, with token: {}", messengerMessage, clientToken);

        //        if (!authenticationHandler.isAuthenticatedClient(request.getHeader("client-name"), request.getHeader("client-token"))) {
//            return new BotReply(BotMessages.getInvalidClientReply());
//        }

        String decodedMessage = URLDecoder.decode(messengerMessage.getMessage(), "UTF-8");
        String userId = clientToken + messengerMessage.getMessengerId();
        User user = userManager.getUserById(userId, messengerMessage.getUserName());

        try {
            return userManager.updateUserAfterExecution(user, () -> applicationContext.getBean(StringUtils.split(decodedMessage, " ")[0], Command.class).executeCommandAndGetReply(decodedMessage, user));
        } catch (NoSuchBeanDefinitionException exception) {
            UserSettings userSettings = user.getShortcuts().stream().filter(userSetting -> userSetting.getKey().equals(StringUtils.split(decodedMessage, " ")[0])).findFirst().orElse(null);
            if (userSettings == null) {
                return executeShortcutCommand.executeCommandAndGetReply(decodedMessage, user, userSettings);
            } else {
                throw exception;
            }
        }
    }
}
