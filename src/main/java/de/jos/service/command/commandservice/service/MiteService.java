package de.jos.service.command.commandservice.service;

import de.jos.service.command.commandservice.database.model.User;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.function.Supplier;

@Service
public class MiteService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${miteurl.host}")
    private String host;
    @Value("${miteurl.port}")
    private int port;
    private URIBuilder uriBuilder;

    @PostConstruct
    private void inititalizeBuilder() {
        uriBuilder = new URIBuilder();
        uriBuilder.setHost(host);
        uriBuilder.setPort(port);
        uriBuilder.setScheme("http");
    }

    public String newEntry(User user,String duration, String comment) {
        uriBuilder.setPath("/newEntry");
        uriBuilder.addParameter("apikey", user.getApiKey());
        uriBuilder.addParameter("projectId", user.getProjectId());
        uriBuilder.addParameter("serviceId", user.getServiceId());
        uriBuilder.addParameter("duration", duration);
        uriBuilder.addParameter("comment", comment);
        try {
            String url = uriBuilder.build().toString();
            return doRequestAndResetUriBuilder(() -> restTemplate.getForObject(url, String.class));
        } catch (URISyntaxException e) {
            uriBuilder.clearParameters();
            return "UriSyntaxException";
        }
    }

    private String doRequestAndResetUriBuilder(Supplier<String> supplier) {
        String result = supplier.get();
        uriBuilder.clearParameters();
        return result;
    }

    public String getAvailableServicesByName(User user, String name) {
        uriBuilder.setPath("/services");
        uriBuilder.addParameter("apiKey", user.getApiKey());
        uriBuilder.addParameter("searchParam", name);
        try {
            String url = uriBuilder.build().toString();
            return doRequestAndResetUriBuilder(() -> restTemplate.getForObject(url, String.class));
        } catch (URISyntaxException e) {
            uriBuilder.clearParameters();
            return "UriSyntaxException";
        }
    }

    public String getAvailableProjectsByName(User user, String name) {
        uriBuilder.setPath("/projects");
        uriBuilder.addParameter("apiKey", user.getApiKey());
        uriBuilder.addParameter("searchParam", name);
        try {
            String url = uriBuilder.build().toString();
            System.out.println("requesting url: " + url);
            return doRequestAndResetUriBuilder(() -> restTemplate.getForObject(url, String.class));
        } catch (URISyntaxException e) {
            uriBuilder.clearParameters();
            return "UriSyntaxException";
        }
    }
}
