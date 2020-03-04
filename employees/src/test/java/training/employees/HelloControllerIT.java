package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import training.employees.components.EventStoreGateway;
import training.employees.hello.HelloController;
import training.employees.hello.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloControllerIT {
    @MockBean
    EventStoreGateway eventStoreGateway;

    @MockBean
    RestTemplate restTemplate2;
    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        assertThat(helloController.hello("asdf")).startsWith("HELLO");
    }

    @Test
    void testSayHello2() {
        assertThat(new HelloService("HELLO").haySello().startsWith("HELLO"));
    }
}
