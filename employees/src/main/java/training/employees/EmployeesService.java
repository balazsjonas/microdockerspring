package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.Employee;
import training.employees.model.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {
    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(id.incrementAndGet(), "John Doe"),
            new Employee(id.incrementAndGet(), "Jane Doe"))));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(id.incrementAndGet(), command.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees(String prefix) {
        log.info("list employees");
        log.debug("list employees (debug)");
        return employees.stream()
                .filter(e -> prefix == null || e.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employees.stream().filter(e -> id == e.getId()).findFirst()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException("Cannot find employee with id " + id));
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employees.stream().filter(e -> id == e.getId()).findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Cannot find employee with id " + id));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        Optional<Employee> employeeToDelete = employees.stream().filter(e -> id == e.getId()).findFirst();
        if (employeeToDelete.isPresent()) {
            employees.remove(employeeToDelete.get());
        }
        else {
            throw new EmployeeNotFoundException("Cannot find employee with id " + id);
        }
    }

    public void deleteAll() {
        id.set(0);
        employees.clear();
    }
}
