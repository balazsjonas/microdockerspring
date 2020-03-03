package training.employees.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private long id;
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
