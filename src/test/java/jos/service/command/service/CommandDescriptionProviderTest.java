package jos.service.command.service;

import jos.service.command.util.CommandDescriptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommandDescriptionProviderTest {
    @Autowired
    private CommandDescriptionProvider commandDescriptionProvider;

    @Test
    public void test() {
        assertThat(commandDescriptionProvider.getAllDescriptions()).contains(CommandDescriptions.NEW_COMMAND_DESCRIPTION);
    }
}
