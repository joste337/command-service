package jos.service.command.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import jos.service.command.database.model.User;
import jos.service.command.exception.CommandHandlerException;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.Service;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component("service")
public class ServiceCommand implements Command {
    @Autowired
    private MiteService miteService;

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        validate(splitUserMessage);

        String response =  miteService.getAvailableServicesByName(user, splitUserMessage[1]);
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

    private void validate(String[] splitUserMessage) {
        if(splitUserMessage.length != 2 && splitUserMessage[1].length() < 2) {
            throw new InvalidCommandOptionsException();
        }
    }
}
