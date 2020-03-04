package training.employees.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import training.employees.model.CreateEventCommand;

@Service
public class EventStoreGateway {

    @Value("${employees.eventstore.url}")
    String eventStoreUrl;

//    @Value("${jms.url}")
//    String jmsUrl;

    RestTemplate restTemplate;

//    private JmsTemplate jmsTemplate;

    public EventStoreGateway(RestTemplateBuilder builder) {
        restTemplate = builder.build();
//        this.jmsTemplate = jmsTemplate;
    }
//    public Gateway(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public void sendEvent(CreateEventCommand eventCommand) {
//        jmsTemplate.convertAndSend("eventsQueue", eventCommand);
        restTemplate.postForObject(eventStoreUrl, eventCommand, String.class);
    }


}
