package com.itbcafrica.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	public String Helloworld() {
		return "Hello world";
	}

	@GetMapping("/hellobean")
	public UserDetails helloworldBean() {
		return new UserDetails("bouendeu", "raphael", "augsburg");
	}
}
