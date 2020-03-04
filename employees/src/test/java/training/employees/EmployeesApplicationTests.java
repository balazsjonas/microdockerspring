package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import training.employees.components.EventStoreGateway;
import training.employees.hello.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeesApplicationTests {
    @MockBean
    EventStoreGateway eventStoreGateway;

    @MockBean
    RestTemplate restTemplate2;
    @Autowired
    HelloService helloService;
    @Test
    void contextLoads() {
    }

    @Test
    public void hello() {
        assertThat(helloService.haySello()).startsWith("Hello");
    }
}
