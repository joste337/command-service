package de.jos.service.command.commandservice.database;

import de.jos.service.command.commandservice.database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
