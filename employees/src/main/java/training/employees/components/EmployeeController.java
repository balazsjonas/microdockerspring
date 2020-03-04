package training.employees.components;

import org.springframework.web.bind.annotation.*;
import training.employees.model.CreateEmployeeCommand;
import training.employees.model.EmployeeDto;
import training.employees.model.UpdateEmployeeCommand;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam(required = false) String prefix) {
        return employeesService.listEmployees(prefix);
    }


    @GetMapping("/{id}")
    public EmployeeDto findEmployeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PostMapping("/{id}")
    public EmployeeDto updateEmployee(@RequestBody UpdateEmployeeCommand command, @PathVariable long id) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeesService.deleteEmployee(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleException() {
        return "Illegális argumentum kivétel";
    }
}
