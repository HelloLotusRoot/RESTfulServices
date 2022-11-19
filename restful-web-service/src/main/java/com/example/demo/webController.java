package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {

	@GetMapping(value = "/index")
	public String index() {
		return "index";
	}

	@GetMapping(value = "/post")
	public String post() {
		return "post";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
}
