package jos.service.command.util;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.model.UriBuilderHelper;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.test.util.ReflectionTestUtils;

public class TestUtils {
    //     private String apiKey = "527f1fde5d5609c5";
//     private String mtr3ID = "2351287";
//     private String developmentID = "253445";

    public static void setWiremockPort(int wiremockPort, UriBuilderHelper uriBuilderHelper) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setHost("localhost");
        uriBuilder.setPort(wiremockPort);
        uriBuilder.setScheme("http");
        ReflectionTestUtils.setField(uriBuilderHelper, "uriBuilder", uriBuilder);
    }

    public static User getTestUser() {
        User testUser = new User();
        testUser.setApiKey("527f1fde5d5609c5");
        testUser.setCurrentProjectId("2351287");
        testUser.setCurrentServiceId("253445");
        return testUser;
    }

    public static UserSettings getTestUserSettings() {
        return new UserSettings("2351287", "253445");
    }
}
