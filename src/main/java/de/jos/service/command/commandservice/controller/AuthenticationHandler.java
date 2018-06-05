package de.jos.service.command.commandservice.controller;

import de.jos.service.command.commandservice.database.TokenAuthRepository;
import de.jos.service.command.commandservice.database.model.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AuthenticationHandler {
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
