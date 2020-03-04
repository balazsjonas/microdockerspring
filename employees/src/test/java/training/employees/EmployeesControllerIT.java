package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import training.employees.components.EmployeesService;
import training.employees.components.EventStoreGateway;
import training.employees.hello.HelloController;
import training.employees.hello.HelloService;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.EmployeeDto;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(properties = "features.hello.special=true")
public class EmployeesControllerIT {
    @MockBean
    EmployeesService employeesService;
    @MockBean
    EventStoreGateway eventStoreGateway;

    @MockBean
    RestTemplate restTemplate2;
    @MockBean
    HelloService helloService;

    @Autowired
    HelloController helloController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreate() throws Exception {
        when(employeesService.createEmployee(any())).thenAnswer(answer -> {
            CreateEmployeeCommand argument = (CreateEmployeeCommand) answer.getArgument(0);

            EmployeeDto employeeDto = new EmployeeDto(100, argument.getName());
            return employeeDto;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Dó Józsi\"}"))
                .andExpect(status().isCreated())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
        .andExpect(jsonPath("$.name", equalTo("Dó Józsi")));

        verify(employeesService, never()).findEmployeeById(anyInt());

    }
}
