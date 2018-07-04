package jos.service.command.service;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.model.UriBuilderHelper;
import jos.service.command.model.CommandServiceReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UriBuilderHelper uriBuilderHelper;

    public String newEntry(User user, UserSettings userSettings, String duration, String comment) {
        String url = uriBuilderHelper.buildUriForNewEntry(user, userSettings, duration, comment);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }

    public String getAvailableServicesByName(User user, String name) {
        String url = uriBuilderHelper.buildUrlForSearchRequest("/service", user, name);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }

    public String getAvailableProjectsByName(User user, String name) {
        String url = uriBuilderHelper.buildUrlForSearchRequest("/project", user, name);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }

    public CommandServiceReply getDeleteEntryOptions(User user, String name) {
        String url = uriBuilderHelper.buildUrlForSearchRequest("/deleteEntry", user, name);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, CommandServiceReply.class);
    }

    public String verifyApiKey(String apiKey) {
        System.out.println("ergergergrege");
        String url = uriBuilderHelper.buildUrlForVerifyApiKey("/verifyApiKey", apiKey);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
