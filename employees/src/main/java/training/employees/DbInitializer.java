package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DbInitializer implements CommandLineRunner {
    private JdbcTemplate jdbcTemplate;

    public DbInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        try {
            jdbcTemplate.execute("drop table employees");
        }catch (Exception e) {
            log.error("nincs itt semmi tábla");
        }
        try {
            jdbcTemplate.execute("create table employees " +
                    "(id bigint auto_increment, emp_name varchar(255), constraint pk_employee primary key (id))");
            jdbcTemplate.execute("insert into employees(emp_name) values ('John Doe');");
            jdbcTemplate.execute("insert into employees(emp_name) values ('Jack Doe');");
        } catch (Exception e) {
            log.error("Basszus");
        }

    }
}
