package training.employees.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
//@Profile("!special-hello")
@ConditionalOnProperty(name = "features.hello.special", matchIfMissing = true)
public class HelloService implements Hello {

    private String hello;

    public HelloService(@Value("${hello}") String hello) {
        this.hello = hello;
    }

    public String haySello() {
        return hello + Instant.now();
    }
}
