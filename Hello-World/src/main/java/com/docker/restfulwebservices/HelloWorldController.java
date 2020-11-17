package com.docker.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World6";
	}

	@GetMapping(path = "/hello-world-pojo")
	public HelloWorldPojo helloWorldBean() {
		return new HelloWorldPojo("Hello World");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldPojo helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldPojo(String.format("Hello World, %s", name));
	}
}
