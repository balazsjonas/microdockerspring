package training.employees;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import training.employees.hello.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeesApplicationTests {

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
