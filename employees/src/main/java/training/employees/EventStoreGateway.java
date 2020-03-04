package training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventStoreGateway {

    @Value("${employees.eventstore.url}")
    String eventStoreUrl;

    RestTemplate restTemplate;

    public EventStoreGateway(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }
//    public Gateway(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public void sendEvent(CreateEventCommand eventCommand) {
        restTemplate.postForObject(eventStoreUrl, eventCommand, String.class);
    }


}
