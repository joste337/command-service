package jos.service.command.manager;


import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.CommandHandlerException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.Arrays;

@Component
public class UriBuilderHelper {
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

    public String buildUriForNewEntry(User user, UserSettings userSettings, String duration, String comment) {
        uriBuilder.clearParameters();

        return buildUrl("/newEntry", user,
                new BasicNameValuePair("projectId", userSettings.getProjectId()),
                new BasicNameValuePair("serviceId", userSettings.getServiceId()),
                new BasicNameValuePair("duration", duration),
                new BasicNameValuePair("comment", comment),
                new BasicNameValuePair("searchParam", null)
        );
    }

    public String buildUrlForSearchRequest(String path, User user, String searchParam) {
        uriBuilder.clearParameters();

        return buildUrl(path, user, new BasicNameValuePair("searchParam", searchParam));
    }

    public String buildUrlForVerifyApiKey(String path, String apiKey) {
        uriBuilder.clearParameters();

        return buildUrl(path, apiKey);
    }

    private String buildUrl(String path, User user, BasicNameValuePair... parameterPairs) {
        uriBuilder.setPath(path);
        uriBuilder.setParameter("apiKey", user.getApiKey());
        Arrays.stream(parameterPairs).forEach(parameterPair -> uriBuilder.addParameter(parameterPair.getName(), parameterPair.getValue()));

        try {
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            throw new CommandHandlerException(e.getMessage(), new CommandServiceReply(BotMessages.INVALID_CHARACTERS_REPLY));
        }
    }

    private String buildUrl(String path, String apiKey) {
        uriBuilder.setPath(path);
        uriBuilder.setParameter("apiKey", apiKey);

        try {
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            throw new CommandHandlerException(e.getMessage(), new CommandServiceReply(BotMessages.INVALID_CHARACTERS_REPLY));
        }
    }
}
