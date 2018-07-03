package jos.service.command.command;

import jos.service.command.database.model.User;
import jos.service.command.database.model.UserSettings;
import jos.service.command.exception.InvalidCommandOptionsException;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import jos.service.command.util.BotMessages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class NewCommandTest {
    @Mock
    private MiteService mockMiteService;
    @Autowired
    private NewCommand newCommand;

    private User testUser;
    private UserSettings testUserSettings;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(newCommand, "miteService", mockMiteService);
        doReturn(null).when(mockMiteService).newEntry(any(), any(), any(), any());
        testUser = new User();
        testUserSettings = new UserSettings("projectId", "serviceId");
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