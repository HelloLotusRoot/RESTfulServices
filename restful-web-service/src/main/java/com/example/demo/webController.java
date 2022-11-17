package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class webController {

	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping(value = "/post")
	public String post() {
		return "post";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "user";
	}
}
