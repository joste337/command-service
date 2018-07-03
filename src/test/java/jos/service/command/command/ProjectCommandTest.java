package jos.service.command.command;

import de.zdf.utils.test.wiremock.WiremockTestBase;
import jos.service.command.database.model.User;
import jos.service.command.model.CommandServiceReply;
import jos.service.command.service.MiteService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectCommandTest {
    private User testUser;

    @Autowired
    private ProjectCommand projectCommand;
    @MockBean
    private MiteService mockMiteService;

    @Rule
    public WiremockTestBase wmockRule = WiremockTestBase.builder()
            .targetUrl("https://www.zdf.de")
            .recordMode(true)
            .build();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(projectCommand, "miteService", mockMiteService);
        String mockProjectsJson = "[ { \"project\": { \"id\": 1234, \"name\": \"testProject\" } } ]";
        doReturn(mockProjectsJson).when(mockMiteService).getAvailableProjectsByName(any(), any());
        testUser = new User();
    }

    @Test
    public void test() {
        CommandServiceReply commandServiceReply = projectCommand.executeCommandAndGetReply(new String[]{"project", "abc"}, testUser);

        assertThat(commandServiceReply.getMessage()).isEqualTo("");
    }
}
