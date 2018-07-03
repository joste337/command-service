package jos.service.command.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import jos.service.command.database.model.User;
import jos.service.command.exception.CommandHandlerException;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.Project;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("project")
public class ProjectCommand implements Command {
    @Autowired
    private MiteService miteService;

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String[] splitUserMessage, User user) {
        validate(splitUserMessage);

        String response =  miteService.getAvailableProjectsByName(user, splitUserMessage[1]);
        JSONObject projectsJson = new JSONObject(response);
        Project[] projects;
        try {
            projects = new ObjectMapper().readValue(projectsJson.get("projects").toString(), Project[].class);
        } catch (Exception e) {
            throw new CommandHandlerException(e.getMessage(), new CommandServiceReply(BotMessages.GENERAL_ERROR_REPLY));
        }

        if (projects.length == 0) {
            return new CommandServiceReply("No projects found");
        } else {
            user.setCurrentProjectId(projects[0].getId());
            user.setCurrentProjectName(projects[0].getName());
            return new CommandServiceReply(String.format(BotMessages.F_SICCESSFUL_SET_PROJECT_REPLY, user.getCurrentProjectName()) + "\n Found Projects:\n" + Arrays.toString(projects));
        }
    }

    private void validate(String[] splitUserMessage) {
        if (splitUserMessage.length != 2 && splitUserMessage[1].length() < 2) {
            throw new InvalidCommandOptionsException();
        }
    }
}
