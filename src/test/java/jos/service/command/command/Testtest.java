package jos.service.command.command;

import jos.service.command.manager.DescriptionManager;
import jos.service.command.util.CommandDescriptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Testtest {
    @Autowired
    DescriptionManager descriptionManager;

    @Test
    public void test() {
        System.out.println(descriptionManager.getAllDescriptions());
    }
}
