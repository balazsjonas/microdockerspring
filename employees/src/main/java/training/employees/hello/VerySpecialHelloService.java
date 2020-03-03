package training.employees.hello;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import training.employees.hello.Hello;

import java.time.Instant;

@Service
//@Profile("special-hello")
@ConditionalOnProperty(name="features.hello.special")
public class VerySpecialHelloService implements Hello {
    public String haySello() {
        return "Hello Spring Boot (special) " + Instant.now();
    }
}
