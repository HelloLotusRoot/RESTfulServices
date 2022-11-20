package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {

	@GetMapping(value = "/home")
	public String index() {
		return "thymeleaf/index";
	}
	
	@GetMapping(value = "/board")
	public String borad() {
		return "board";
	}
	
	@GetMapping(value = "/inserPost")
	public String inserPost() {
		return "inserPost";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "/loginAction")
	public String loginAction() {
		return "loginAction";
	}
	
	@GetMapping(value = "/main")
	public String main() {
		return "main";
	}
	
	@GetMapping(value = "/post")
	public String post() {
		return "post";
	}
}
