package training.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@PathParam("name") String name) {
        return "hello " + name+" at "+ LocalDateTime.now();
    }
}
