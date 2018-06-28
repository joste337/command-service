package jos.service.command.database;

import jos.service.command.database.model.TokenAuth;
import org.springframework.data.repository.CrudRepository;

public interface TokenAuthRepository extends CrudRepository<TokenAuth, String> {
}
