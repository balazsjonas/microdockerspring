package training.employees;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import training.employees.hello.HelloController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled
public class HelloControllerIT {

    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        assertThat(helloController.hello("asdf")).startsWith("HELLO2");
    }
}
