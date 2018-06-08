package de.jos.service.command.commandservice.manager;

import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.database.UserRepository;
import de.jos.service.command.commandservice.database.model.User;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class UserManager {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserRepository userRepository;


    public Optional<String> isNotVeriefiedUser(User user) {
        StringBuilder replyMessage = new StringBuilder();

        appendValueIfNameIsNull(replyMessage,
                new BasicNameValuePair(user.getApiKey(),botMessages.getNoApiKeyProvidedReply()),
                new BasicNameValuePair(user.getCurrentProjectId(), botMessages.getNoProjectIdProvidedReply()),
                new BasicNameValuePair(user.getCurrentServiceId(), botMessages.getNoServideIdProvidedReply())
        );

        return replyMessage.length() == 0 ? Optional.empty() : Optional.of(replyMessage.toString());
    }

    public Optional<String> isNotRegisteredUser(User user) {
        return user.getApiKey() == null ? Optional.of(botMessages.getNoApiKeyProvidedReply()) : Optional.empty();
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

    public User getUserById(String id, String userName) {
        return userRepository.findById(id).orElse(new User(id, userName));
    }
}
