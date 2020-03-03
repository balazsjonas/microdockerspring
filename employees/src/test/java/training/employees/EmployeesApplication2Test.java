package training.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import training.employees.hello.HelloController;
import training.employees.hello.HelloService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeesApplication2Test {
    @Mock
    HelloService helloService;

    @InjectMocks
    HelloController helloController;


    @Test
    void hello() {

        when(helloService.haySello()).thenReturn("hello world");
        assertEquals("HELLO WORLD", helloController.hello("asdf"));

    }
}
