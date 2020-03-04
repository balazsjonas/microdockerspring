package training.employees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.EmployeeDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(statements = "delete from employees") // beforeach
public class FullStackIT {
    @Autowired
    EmployeeController controller;

    @AfterEach
    public void setup() {
        System.out.println(controller.listEmployees(""));
    }

    @Test
    void testCreate() {
        var employee = controller.createEmployee(new CreateEmployeeCommand("John Doe"));
        assertEquals("John Doe", controller.findEmployeById(employee.getId()).getName());
        assertThat(controller.listEmployees("").size()).isEqualTo(1);
    }

    @Test
    void testDelete() {
        EmployeeDto jane_doe = controller.createEmployee(new CreateEmployeeCommand("Jane Doe"));
        var john = controller.createEmployee(new CreateEmployeeCommand("John Doe"));
        controller.deleteEmployee(john.getId());
        assertEquals("Jane Doe", controller.findEmployeById(jane_doe.getId()).getName());
        assertThat(controller.listEmployees("").size()).isEqualTo(1);
    }
}
