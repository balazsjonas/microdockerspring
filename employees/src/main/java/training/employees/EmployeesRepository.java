package training.employees;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.employees.model.Employee;

public interface EmployeesRepository extends MongoRepository<Employee, String> {
}
