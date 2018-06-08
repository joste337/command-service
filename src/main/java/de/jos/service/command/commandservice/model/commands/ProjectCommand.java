package de.jos.service.command.commandservice.model.commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.model.Project;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("project")
public class ProjectCommand extends AbstractCommand {
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }

        String response =  miteService.getAvailableProjectsByName(user, StringUtils.split(userMessage, " ")[1]);
        JSONObject projectsJson = new JSONObject(response);
        Project[] projects;
        try {
            projects = new ObjectMapper().readValue(projectsJson.get("projects").toString(), Project[].class);
        } catch (Exception e) {
            return "mapping exception";
        }

        if (projects.length == 0) {
            return "no projects found";
        } else {
            user.setCurrentProjectId(projects[0].getId());
            user.setCurrentProjectName(projects[0].getName());
            return botMessages.getSuccessfullySetProjectIdByNameReply(user.getCurrentProjectName()) + "\n Found Projects:\n" + Arrays.toString(projects);
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
