package jos.service.command.controller;

import jos.service.command.command.ExecuteShortcutCommand;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.manager.UserManager;
import jos.service.command.model.CommandServiceReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAdvice.class);
    @Autowired
    private UserManager userManager;
    @Autowired
    private ExecuteShortcutCommand executeShortcutCommand;

    @ExceptionHandler({NoSuchBeanDefinitionException.class})
    @ResponseBody
    public CommandServiceReply handleNoSuchBeanDefinitionException(NoSuchBeanDefinitionException exception, HttpServletResponse response) {
        LOGGER.warn("NoSuchBeanDefinitionException: {}", exception.getMessage());
        response.setStatus(200);
        return new CommandServiceReply(exception.getMessage());
    }

    @ExceptionHandler({InvalidCommandOptionsException.class})
    @ResponseBody
    public CommandServiceReply handleInvalidCommandOptionsException(InvalidCommandOptionsException exception, HttpServletResponse response) {
        LOGGER.warn("InvalidCommandOptionsException: {}", exception.getMessage());
        response.setStatus(200);
        return new CommandServiceReply(exception.getMessage());
    }

}
