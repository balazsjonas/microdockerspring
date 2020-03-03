package training.employees.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController // mindenre rárakja a ResponseBody-t
public class HelloController {
    private final Hello hello;

    public HelloController(Hello hello) {
        this.hello = hello;
    }


    @GetMapping("/hello")
    @ResponseBody
    public String hello(@PathParam("name") String name) {
        return hello.haySello().toUpperCase();
    }
}
