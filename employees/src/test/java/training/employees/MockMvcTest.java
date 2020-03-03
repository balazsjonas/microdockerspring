package training.employees;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import training.employees.model.EmployeeDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MockMvcTest {
    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @BeforeEach
    public  void setup() {
        jdbcTemplate.execute("drop table emp_name");
    }


    @Test
    void createEmployee() {
        restTemplate.postForObject("/api/employees", "{\"name\": \"Dó Józsi\"}", EmployeeDto.class);

        List<EmployeeDto> employees = restTemplate.getForObject("/api/employees", List.class);
        System.out.println(employees);

    }

    @Test
    @DirtiesContext
    void listEmployees() {
        System.out.println("hello@" + port);
        System.out.println(restTemplate.getRootUri());

        // NOTE: Type erasure
        List<EmployeeDto> employees = restTemplate.getForObject("/api/employees", List.class);
        employees = restTemplate.exchange("/api/employees", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {
                }).getBody();
        System.out.println(employees);

        assertThat(employees)
                .extracting(EmployeeDto::getName)
                .containsExactly("John Doe", "Jane Doe");
    }
}
