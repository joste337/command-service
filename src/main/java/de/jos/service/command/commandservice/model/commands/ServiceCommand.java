package de.jos.service.command.commandservice.model.commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.Service;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Arrays;

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
            return "mapping exception";
        }

        if (services.length == 0) {
            return "no services found";
        } else {
            user.setServiceId(services[0].getId());
            user.setServiceName(services[0].getName());
            return botMessages.getSuccessfullySetServiceIdByNameReply(user.getServiceName()) + "\n Found Services:\n" + Arrays.asList(services).toString();
        }
    }

    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        return splitMessage.length > 1 && splitMessage[1].length() > 2;
    }
}
