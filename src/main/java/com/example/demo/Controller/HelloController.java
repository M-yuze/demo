package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class HelloController {


    @GetMapping("/test")
    public String sayHello(){
        return "hello world!";
    }
}
