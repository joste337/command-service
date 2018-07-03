package jos.service.command.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelpCommandTest {
    @Autowired
    private HelpCommand helpCommand;

    @Test
    public void getHelpCommandTest() {
        assertThat(helpCommand.executeCommandAndGetReply(null, null).getMessage()).contains("Command");
    }

}
