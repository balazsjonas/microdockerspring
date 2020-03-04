package training.employees.components;

import org.springframework.data.jpa.repository.JpaRepository;
import training.employees.model.Employee;

public interface DataEmployeesRepository extends JpaRepository<Employee, Long> {
}
