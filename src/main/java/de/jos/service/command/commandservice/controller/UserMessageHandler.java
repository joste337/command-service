package de.jos.service.command.commandservice.controller;

import de.jos.service.command.commandservice.database.TokenAuthRepository;
import de.jos.service.command.commandservice.manager.UserManager;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import de.jos.service.command.commandservice.model.commands.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Optional;

@RestController
public class UserMessageHandler implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageHandler.class);

    private ApplicationContext appContext;
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserManager userManager;
    @Autowired
    private AuthenticationHandler authenticationHandler;


    @GetMapping("/handleMessage")
    public BotReply handleMessage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("message") String message,
                                  @RequestParam("id") String messengerId,
                                  @RequestParam("userName") String userName) {

//        if (!authenticationHandler.isAuthenticatedClient(request.getHeader("client-name"), request.getHeader("client-token"))) {
//            return new BotReply(BotMessages.getInvalidClientReply());
//        }

        Command command;
        try {
            message = URLDecoder.decode(message, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("html decoding failed");
        }
        LOGGER.debug("decoded html message: {}", message);

        try {
            command = appContext.getBean(StringUtils.split(message, " ")[0], Command.class);
        } catch (NoSuchBeanDefinitionException e) {
            return new BotReply(botMessages.getInvalidCommandReply());
        }

        String userId = request.getHeader("client-token") + messengerId;

        BotReply botReply;

        User user = userManager.getUserById(userId, userName);

        if (command instanceof NewCommand) {
            botReply = new BotReply(userManager.isNotVeriefiedUser(user).orElse(command.executeCommandAndGetReply(message, user)));
        } else if (!(command instanceof HelpCommand) && !(command instanceof StartCommand) && !(command instanceof RegisterCommand)){
            botReply = new BotReply(userManager.isNotRegisteredUser(user).orElse(command.executeCommandAndGetReply(message, user)));
        } else {
            botReply = new BotReply(command.executeCommandAndGetReply(message, user));
        }
        userManager.saveUser(user);
        return botReply;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.appContext = applicationContext;
    }
}
