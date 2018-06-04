package de.jos.service.command.commandservice.controller;

import de.jos.service.command.commandservice.exception.CommandHandlerException;
import de.jos.service.command.commandservice.model.BotReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@ControllerAdvice
public class ErrorAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAdvice.class);
    @Autowired
    BotMessages botMessages;

    @ExceptionHandler({NoSuchBeanDefinitionException.class})
    @ResponseBody
    public BotReply handleNoSuchBeanDefinitionException(NoSuchBeanDefinitionException exception, HttpServletResponse response) {
        LOGGER.info("Invalid command provided: {}", exception.getMessage());
        response.setStatus(200);
        return new BotReply(botMessages.getInvalidCommandReply());
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
        LOGGER.error("Exception: {}", exception.getMessage());
        response.setStatus(200);
        return new BotReply(botMessages.getGeneralErrorReply());
    }
}
