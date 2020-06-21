package org.company.api.rest;


import javax.ws.rs.Path;

import org.company.domain.Consumer;
import org.company.repository.ConsumerRepository;
import org.company.services.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestConsumerService {

//    public static final String BASE_PATH = "/consumers";


    ConsumerRepository consumerRepository = ConsumerRepository.getInstance();
    @GetMapping("/getuserbyid")
    public Consumer getUser(@RequestParam(value = "id", defaultValue = "0") String id) throws Exception {
        Consumer c = consumerRepository.getByLocalId(id);
        return c;
    }
}
