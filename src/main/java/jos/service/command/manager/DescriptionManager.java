package jos.service.command.manager;

import jos.service.command.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptionManager {
    @Autowired
    private List<Command> list;

    public String getAllDescriptions() {
        StringBuilder result = new StringBuilder();
        list.forEach(command -> result.append(command.getDescription()).append("\n"));
        return result.toString();
    }
}
