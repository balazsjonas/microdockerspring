package training.employees;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmployeesApplication {

    @Value("${employees.eventstore.url}")
    String eventStoreUrl;

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(eventStoreUrl,  String.class);
        return restTemplate;
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
