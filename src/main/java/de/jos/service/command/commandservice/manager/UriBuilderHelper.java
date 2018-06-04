package de.jos.service.command.commandservice.manager;

import de.jos.service.command.commandservice.controller.BotMessages;
import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.exception.CommandHandlerException;
import de.jos.service.command.commandservice.model.BotReply;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.Arrays;

@Component
public class UriBuilderHelper {
    @Autowired
    private BotMessages botMessages;
    private URIBuilder uriBuilder;
    @Value("${miteurl.host}")
    private String host;
    @Value("${miteurl.port}")
    private int port;

    @PostConstruct
    public void setUpUriBuilder() {
        uriBuilder = new URIBuilder();
        uriBuilder.setHost(host);
        uriBuilder.setPort(port);
        uriBuilder.setScheme("http");
    }

    public String buildUriForNewEntry(User user, String duration, String comment) {
        uriBuilder.clearParameters();

        return buildUrl("/newEntry", user,
                new BasicNameValuePair("projectId", user.getProjectId()),
                new BasicNameValuePair("serviceId", user.getServiceId()),
                new BasicNameValuePair("duration", duration),
                new BasicNameValuePair("comment", comment),
                new BasicNameValuePair("searchParam", null)
        );
    }

    public String buildUrlForSearchRequest(String path, User user, String searchParam) {
        uriBuilder.clearParameters();

        return buildUrl(path, user, new BasicNameValuePair("searchParam", searchParam));
    }

    private String buildUrl(String path, User user, BasicNameValuePair... parameterPairs) {
        uriBuilder.setPath(path);
        uriBuilder.setParameter("apiKey", user.getApiKey());
        Arrays.stream(parameterPairs).forEach(parameterPair -> uriBuilder.addParameter(parameterPair.getName(), parameterPair.getValue()));

        try {
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            throw new CommandHandlerException(e.getMessage(), new BotReply(botMessages.getInvalidCharactersReply()));
        }
    }
}
