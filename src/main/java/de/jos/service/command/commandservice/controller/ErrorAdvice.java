package de.jos.service.command.commandservice.controller;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.database.model.UserSettings;
import de.jos.service.command.commandservice.exception.CommandHandlerException;
import de.jos.service.command.commandservice.manager.UserManager;
import de.jos.service.command.commandservice.model.BotReply;
import de.jos.service.command.commandservice.model.commands.ExecuteShortcutCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ControllerAdvice
public class ErrorAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAdvice.class);
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ExecuteShortcutCommand executeShortcutCommand;

    @ExceptionHandler({NoSuchBeanDefinitionException.class})
    @ResponseBody
    public BotReply handleNoSuchBeanDefinitionException(NoSuchBeanDefinitionException exception, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

        String message = URLDecoder.decode(request.getParameter("message"), "UTF-8");
        String userId = request.getHeader("client-token") + request.getParameter("id");
        User user = userManager.getUserById(userId, request.getParameter("userName"));

        return new BotReply(executeShortcutCommand.executeCommandAndGetReply(message, user));
    }

    @ExceptionHandler({UnsupportedEncodingException.class})
    @ResponseBody
    public BotReply handleUnsupportedEncodingException(UnsupportedEncodingException exception, HttpServletResponse response) {
        LOGGER.error("UnsupportedEncodingException: {}", exception.getMessage());
        response.setStatus(200);
        return new BotReply(botMessages.getInvalidCharactersReply());
    }

    @ExceptionHandler({CommandHandlerException.class})
    @ResponseBody
    public BotReply handleCommandHandlerException(CommandHandlerException exception, HttpServletResponse response) {
        LOGGER.warn("CommandHandlerException: {}", exception.getMessage());
        response.setStatus(200);
        return exception.getBotReply();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BotReply handleCommandHandlerException(Exception exception, HttpServletResponse response) {
        LOGGER.error("Exception: {}", exception.getMessage(), exception);
        response.setStatus(200);
        return new BotReply(botMessages.getGeneralErrorReply());
    }
}
