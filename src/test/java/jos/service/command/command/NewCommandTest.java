package jos.service.command.command;

import de.zdf.utils.test.wiremock.WiremockTestRule;
import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.model.UriBuilderHelper;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import jos.service.command.util.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewCommandTest {
    @Rule
    public WiremockTestRule wmockRule = WiremockTestRule.builder()
            .targetUrl("http://localhost:8700")
            .recordMode(false)
            .build();

    @Mock
    private MiteService mockMiteService;
    @Autowired
    private NewCommand newCommand;
    @Autowired
    private UriBuilderHelper uriBuilderHelper;

    private User testUser;
    private UserSettings testUserSettings;

    @Before
    public void setUp() {
        TestUtils.setWiremockPort(wmockRule.getWiremockPort(), uriBuilderHelper);
        testUser = TestUtils.getTestUser();
        testUserSettings = TestUtils.getTestUserSettings();
    }

    @Test
    public void shouldReturnSuccessfulReply() {
        String[] userMessage = {"new", "2:00", "abcd"};

        CommandServiceReply commandServiceReply = newCommand.executeCommandAndGetReply(userMessage, testUser, testUserSettings);

        assertThat(commandServiceReply.getMessage()).isEqualTo(String.format(BotMessages.F_SUCCESSFUL_ENTRY_REPLY, "120", "abcd"));
    }

    @Test
    public void shouldThrowInvalidCommandArgumentsWithInvalidDuration() {
        String[] userMessage = {"sc", "23", "abcd"};

        assertThatThrownBy(() -> newCommand.executeCommandAndGetReply(userMessage, testUser, testUserSettings))
                .isInstanceOf(InvalidCommandOptionsException.class)
                .hasMessage(BotMessages.INVALID_COMMAND_ARGUMENTS_REPLY);
    }

    @Test
    public void shouldThrowInvalidCommandArgumentsWithoutComment() {
        String[] userMessage = {"sc", "2:00"};

        assertThatThrownBy(() -> newCommand.executeCommandAndGetReply(userMessage, testUser, testUserSettings))
                .isInstanceOf(InvalidCommandOptionsException.class)
                .hasMessage(BotMessages.INVALID_COMMAND_ARGUMENTS_REPLY);
    }
}