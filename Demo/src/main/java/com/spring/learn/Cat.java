package com.spring.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cat {
	
@Autowired
	Dog dog;

@GetMapping("/call")
	public String callDog() {
		return dog.bark();
	}
}
