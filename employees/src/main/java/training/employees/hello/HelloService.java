package training.employees.hello;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
//@Profile("!special-hello")
@ConditionalOnProperty(name="features.hello.special", matchIfMissing = true)
public class HelloService implements Hello {
    public String haySello() {
        return "Hello Spring Boot (default) " + Instant.now();
    }
}
