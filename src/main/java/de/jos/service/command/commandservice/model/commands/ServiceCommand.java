package de.jos.service.command.commandservice.model.commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.exception.CommandHandlerException;
import de.jos.service.command.commandservice.model.BotReply;
import de.jos.service.command.commandservice.model.Service;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("service")
public class ServiceCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }

        String response =  miteService.getAvailableServicesByName(user, StringUtils.split(userMessage, " ")[1]);
        JSONObject servicesJson = new JSONObject(response);
        Service[] services;
        try {
            services = new ObjectMapper().readValue(servicesJson.get("services").toString(), Service[].class);
        } catch (Exception e) {
            throw new CommandHandlerException(e.getMessage(), new BotReply(botMessages.getGeneralErrorReply()));
        }

        if (services.length == 0) {
            return "no services found";
        } else {
            user.setCurrentServiceId(services[0].getId());
            user.setCurrentServiceName(services[0].getName());
            return botMessages.getSuccessfullySetServiceIdByNameReply(user.getCurrentServiceName()) + "\n Found Services:\n" + Arrays.toString(services);
        }
    }

    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        return splitMessage.length > 1 && splitMessage[1].length() > 2;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
