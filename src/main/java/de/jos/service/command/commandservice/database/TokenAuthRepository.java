package de.jos.service.command.commandservice.database;

import de.jos.service.command.commandservice.database.model.TokenAuth;
import org.springframework.data.repository.CrudRepository;

public interface TokenAuthRepository extends CrudRepository<TokenAuth, String> {
}
