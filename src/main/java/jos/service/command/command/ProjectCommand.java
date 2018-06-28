package jos.service.command.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import jos.service.command.database.model.User;
import jos.service.command.exception.CommandHandlerException;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.Project;
import jos.service.command.util.BotMessages;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("project")
public class ProjectCommand extends AbstractCommand {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public CommandServiceReply executeCommandAndGetReply(String userMessage, User user) {
        String response =  miteService.getAvailableProjectsByName(user, StringUtils.split(userMessage, " ")[1]);
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

    @Override
    public void isInvalidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if (splitMessage.length > 1 && splitMessage[1].length() > 2) {
            return;
        } else {
            throw new InvalidCommandOptionsException();
        }
    }
}
