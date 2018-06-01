package de.jos.service.command.commandservice.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TokenAuth {
    @Id
    private String clientName;
    private String token;

    public TokenAuth(String clientName, String token) {
        this.clientName = clientName;
        this.token = token;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
