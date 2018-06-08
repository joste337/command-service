package de.jos.service.command.commandservice.service;

import de.jos.service.command.commandservice.database.model.User;
import de.jos.service.command.commandservice.database.model.UserSettings;
import de.jos.service.command.commandservice.manager.UriBuilderHelper;
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

    public String verifyApiKey(String apiKey) {
        String url = uriBuilderHelper.buildUrlForVerifyApiKey("/verifyApiKey", apiKey);

        LOGGER.info("Requesting url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
