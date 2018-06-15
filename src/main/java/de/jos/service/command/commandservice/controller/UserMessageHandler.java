package de.jos.service.command.commandservice.controller;

import de.jos.service.command.commandservice.database.TokenAuthRepository;
import de.jos.service.command.commandservice.manager.UserManager;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.BotReply;
import de.jos.service.command.commandservice.model.MessageOption;
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
import java.io.UnsupportedEncodingException;
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
                                  @RequestParam("userName") String userName) throws UnsupportedEncodingException {

//        if (!authenticationHandler.isAuthenticatedClient(request.getHeader("client-name"), request.getHeader("client-token"))) {
//            return new BotReply(BotMessages.getInvalidClientReply());
//        }

        message = URLDecoder.decode(message, "UTF-8");
        LOGGER.debug("decoded html message: {}", message);

        return new BotReply("Message", new MessageOption[] {new MessageOption("option1", "url1"), new MessageOption("option2", "url2"), new MessageOption("option3", "url3")});


//        Command command = appContext.getBean(StringUtils.split(message, " ")[0], Command.class);
//
//        String userId = request.getHeader("client-token") + messengerId;
//        User user = userManager.getUserById(userId, userName);
//
//        BotReply botReply;

//        if (command instanceof NewCommand) {
//            botReply = userManager.isNotVeriefiedUser(user).orElse(command.executeCommandAndGetReply(message, user));
//        } else if (!(command instanceof HelpCommand) && !(command instanceof StartCommand) && !(command instanceof RegisterCommand)){
//            botReply = userManager.isNotRegisteredUser(user).orElse(command.executeCommandAndGetReply(message, user));
//        } else {
//            botReply = command.executeCommandAndGetReply(message, user);
//        }
//
//        userManager.saveUser(user);
//        return botReply;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.appContext = applicationContext;
    }
}
