package jos.service.command.controller;

import jos.service.command.manager.MessageHandleManager;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.MessengerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class UserMessageController {
    @Autowired
    private MessageHandleManager messageHandleManager;

    @GetMapping("/handleMessage")
    public CommandServiceReply handleMessage(HttpServletRequest request,
                                             @Validated @ModelAttribute MessengerMessage messengerMessage) throws UnsupportedEncodingException {
        return messageHandleManager.handleMessage(request.getHeader("client-token"), messengerMessage);
    }
}
