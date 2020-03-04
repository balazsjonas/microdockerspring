package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.Employee;
import training.employees.model.EmployeeDto;

import javax.transaction.Transactional;
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

    private DataEmployeesRepository employeesRepository;

//    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Employee(id.incrementAndGet(), "John Doe"),
//            new Employee(id.incrementAndGet(), "Jane Doe"))));

    public EmployeesService(ModelMapper modelMapper, DataEmployeesRepository employeesRepository) {
        this.modelMapper = modelMapper;
        this.employeesRepository = employeesRepository;
    }

    @Transactional
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

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesRepository.findById(id).orElseThrow(), EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Optional<Employee> byId = employeesRepository.findById(id);
        byId.ifPresent(e -> e.setName(command.getName()));
        return modelMapper.map(byId.orElseThrow(), EmployeeDto.class);
    }

    @Transactional
    public void deleteEmployee(long id) {
        employeesRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        id.set(0);
        employeesRepository.deleteAll();

    }
}
