package jos.service.command.service;

import jos.service.command.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandDescriptionProvider {
    @Autowired
    private List<Command> commandList;

    public String getAllDescriptions() {
        StringBuilder result = new StringBuilder();
        commandList.forEach(command -> result.append(command.getDescription()).append("\n"));
        return result.toString();
    }
}
