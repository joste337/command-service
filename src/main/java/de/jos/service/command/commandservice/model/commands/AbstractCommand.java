package de.jos.service.command.commandservice.model.commands;

import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.service.MiteService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommand implements Command {
    @Autowired
    protected MiteService miteService;
    @Autowired
    protected BotMessages botMessages;
}
