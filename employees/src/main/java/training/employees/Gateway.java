package training.employees;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Gateway {

    RestTemplate restTemplate;

    public Gateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
