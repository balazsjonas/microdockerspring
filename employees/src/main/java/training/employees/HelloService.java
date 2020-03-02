package training.employees;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HelloService {
    public String haySello() {
        return "Hello Spring Boot (---) " + Instant.now();
    }
}
