package com.docker.restapimysql.helloworldmysql.controller;

import com.docker.restapimysql.helloworldmysql.pojo.HelloWorldPojo;
import com.docker.restapimysql.helloworldmysql.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private HelloWorldService service;

    @Autowired
    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping(path = "/hello-world")
    public HelloWorldPojo getHelloWorld() {
        return new HelloWorldPojo(service.getMessage().getMessage());
    }
}
