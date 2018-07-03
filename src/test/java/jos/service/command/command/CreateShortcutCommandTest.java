package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.util.BotMessages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateShortcutCommandTest {
    @Autowired
    private CreateShortcutCommand createShortcutCommand;
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User("id", "token", "projectName", "projectId", "serviceName", "serviceId");
    }

    @Test
    public void shouldReturnSuccessfulReply() {
        String[] userMessage = {"shortcut", "myShortcut"};

        CommandServiceReply commandServiceReply = createShortcutCommand.executeCommandAndGetReply(userMessage, testUser);

        assertThat(testUser.getShortcuts().get(0).getKey()).isEqualTo("myShortcut");
        assertThat(testUser.getShortcuts().get(0).getProjectId()).isEqualTo("projectId");
        assertThat(commandServiceReply.getMessage()).isEqualTo(BotMessages.SUCCESSFULL_SHORTCUT_REPLY);
    }

    @Test
    public void shouldThrowInvalidCommandArgumentsWithoutShortcutName() {
        String[] userMessage = {"shortcut"};

        assertThatThrownBy(() -> createShortcutCommand.executeCommandAndGetReply(userMessage, testUser))
                .isInstanceOf(InvalidCommandOptionsException.class)
                .hasMessage(BotMessages.INVALID_COMMAND_ARGUMENTS_REPLY);
    }

    @Test
    public void shouldThrowInvalidCommandArgumentsWithoutSettings() {
        User user = new User("id", "name");
        String[] userMessage = {"shortcut", "sc"};

        assertThatThrownBy(() -> createShortcutCommand.executeCommandAndGetReply(userMessage, user))
                .isInstanceOf(InvalidCommandOptionsException.class)
                .hasMessage(BotMessages.INVALID_COMMAND_ARGUMENTS_REPLY);
    }
}
