package jos.service.command.manager;

import jos.service.command.database.UserRepository;
import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class UserManager {
    @Autowired
    private UserRepository userRepository;


    public Optional<CommandServiceReply> isNotVeriefiedUser(User user) {
        StringBuilder replyMessage = new StringBuilder();

        appendValueIfNameIsNull(replyMessage,
                new BasicNameValuePair(user.getApiKey(),BotMessages.NO_API_KEY_PROVIDED_REPLY),
                new BasicNameValuePair(user.getCurrentProjectId(), BotMessages.NO_PROJECT_ID_PROVIDED_REPLY),
                new BasicNameValuePair(user.getCurrentServiceId(), BotMessages.NO_SERVICE_ID_PROVIDED_REPLY)
        );

        return replyMessage.length() == 0 ? Optional.empty() : Optional.of(new CommandServiceReply(replyMessage.toString()));
    }

    public Optional<CommandServiceReply> isNotRegisteredUser(User user) {
        return user.getApiKey() == null ? Optional.of(new CommandServiceReply(BotMessages.NO_API_KEY_PROVIDED_REPLY)) : Optional.empty();
    }

    private void appendValueIfNameIsNull(StringBuilder stringBuilder, BasicNameValuePair... pairs) {
        Arrays.asList(pairs).forEach(pair -> {
            if (pair.getName() == null) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append(pair.getValue());
                } else {
                    stringBuilder.append("\n").append(pair.getValue());
                }
            }
        });
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public CommandServiceReply updateUserAfterExecution(User user, Supplier<CommandServiceReply> supplier) {
        CommandServiceReply botReply = supplier.get();
        userRepository.save(user);
        return botReply;
    }

    public User getUserById(String id, String userName) {
        return userRepository.findById(id).orElse(new User(id, userName));
    }
}
