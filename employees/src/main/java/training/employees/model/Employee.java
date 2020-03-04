package training.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("employees")
public class Employee {

//    @MongoId
    @Id
    private String id;

    private String name;


    public Employee(String name) {
        this.name = name;
    }
}
