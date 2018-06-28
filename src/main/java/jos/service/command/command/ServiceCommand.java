package jos.service.command.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import jos.service.command.database.model.User;
import jos.service.command.exception.CommandHandlerException;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.Service;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component("service")
public class ServiceCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        String response =  miteService.getAvailableServicesByName(user, StringUtils.split(userMessage, " ")[1]);
        JSONObject servicesJson = new JSONObject(response);
        Service[] services;
        try {
            services = new ObjectMapper().readValue(servicesJson.get("services").toString(), Service[].class);
        } catch (Exception e) {
            throw new CommandHandlerException(e.getMessage(), new CommandServiceReply(BotMessages.GENERAL_ERROR_REPLY));
        }

        if (services.length == 0) {
            return new CommandServiceReply("No services found");
        } else {
            user.setCurrentServiceId(services[0].getId());
            user.setCurrentServiceName(services[0].getName());
            return new CommandServiceReply(String.format(BotMessages.F_SICCESSFUL_SET_SERVICE_REPLY, user.getCurrentServiceName()) + "\n Found Projects:\n" + Arrays.toString(services));
        }
    }

    @Override
    public void isInvalidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if(splitMessage.length > 1 && splitMessage[1].length() > 2) {
            return;
        } else {
            throw new InvalidCommandOptionsException();
        }
    }
}
