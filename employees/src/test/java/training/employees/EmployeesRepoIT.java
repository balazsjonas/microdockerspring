package training.employees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import training.employees.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeesRepoIT {

    @Autowired
    DataEmployeesRepository repository;

    @AfterEach
    public void tearDown() {
        System.out.println(repository.findAll());
    }
    @Test
     void testCreate() {
        repository.save(new Employee("John Doe"));
        assertThat(repository.findAll())
                .extracting(Employee::getName)
                .contains("John Doe");
     }

     @Test
     void testDelete() {
         Employee john_doe = repository.save(new Employee("John Doe"));
         repository.deleteById(john_doe.getId());
         assertThat(repository.findAll()).isEmpty();
     }
}
