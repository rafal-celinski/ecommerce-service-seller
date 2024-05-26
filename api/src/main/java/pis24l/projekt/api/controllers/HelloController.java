package pis24l.projekt.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class HelloController {

    @GetMapping("/hello world")
    public String helloWorld() {
        return "Hello World!";
    }
}

