package jos.service.command.controller;

import jos.service.command.database.TokenAuthRepository;
import jos.service.command.database.model.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthenticationController {
    @Autowired
    private TokenAuthRepository tokenAuthRepository;

    @GetMapping("/authenticate")
    public String authenticateClient(@RequestParam("client-name") String clientName) {
        TokenAuth tokenAuth = tokenAuthRepository.findById(clientName).orElse(new TokenAuth(clientName, UUID.randomUUID().toString()));
        tokenAuthRepository.save(tokenAuth);
        return tokenAuth.getToken();
    }

    public boolean isAuthenticatedClient(String clientName, String token) {
        return tokenAuthRepository.findById(clientName).isPresent() && tokenAuthRepository.findById(clientName).get().getToken().equals(token);
    }
}
