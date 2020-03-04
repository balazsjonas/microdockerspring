package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.Employee;
import training.employees.model.EmployeeDto;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {
    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    private EmployeesRepository employeesRepository;


    public EmployeesService(ModelMapper modelMapper, EmployeesRepository employeesRepository) {
        this.modelMapper = modelMapper;
        this.employeesRepository = employeesRepository;
    }


    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = employeesRepository.save(new Employee(command.getName()));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees(String prefix) {
        log.info("list employees");
        log.debug("list employees (debug)");
        return employeesRepository.findAll().stream()
                .filter(e -> prefix == null || e.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(String id) {
        return modelMapper.map(employeesRepository.findById(id).orElseThrow(), EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(String id, UpdateEmployeeCommand command) {
        Optional<Employee> byId = employeesRepository.findById(id);
        byId.ifPresent(e -> e.setName(command.getName()));
        return modelMapper.map(byId.orElseThrow(), EmployeeDto.class);
    }

    public void deleteEmployee(String id) {
        employeesRepository.deleteById(id);
    }

    public void deleteAll() {
        id.set(0);
        employeesRepository.deleteAll();

    }
}
