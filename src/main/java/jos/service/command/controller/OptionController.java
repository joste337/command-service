package jos.service.command.controller;

import jos.service.command.model.CommandServiceReply;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OptionController {
    private RestTemplate restTemplate;

    public OptionController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/deleteOption")
    public CommandServiceReply deleteOption(@RequestParam String miteRequestUrl) {
        restTemplate.delete(miteRequestUrl);

        return new CommandServiceReply("Deleted");
    }
}
